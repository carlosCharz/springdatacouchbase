package com.wedevol.springdatacouchbase.core.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.couchbase.config.BeanNames;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlParams;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.wedevol.springdatacouchbase.core.dao.UserCounterRepository;
import com.wedevol.springdatacouchbase.core.dao.UserRepository;
import com.wedevol.springdatacouchbase.core.dao.doc.UserBasicDoc;
import com.wedevol.springdatacouchbase.core.dao.doc.UserDoc;
import com.wedevol.springdatacouchbase.core.exception.ApiException;
import com.wedevol.springdatacouchbase.core.exception.ErrorType;
import com.wedevol.springdatacouchbase.core.service.UserService;
import com.wedevol.springdatacouchbase.core.util.Util;

/**
 * Service that manages the valid operations over the user repository.
 *
 * @author Charz++
 */

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserCounterRepository userCounterRepo;

    // TODO move this method to a repo class
    // NOTE: add the qualifier in case you have multiple buckets in your configuration otherwise remove it
    @Autowired
    @Qualifier(BeanNames.COUCHBASE_TEMPLATE)
    private CouchbaseTemplate defaultTemplate; // NOTE: used for the example of N1QL query with cover index

    @Override
    public UserDoc findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public UserDoc findByIdOrThrow(Long id) {
        Optional<UserDoc> userObj = userRepo.findById(UserDoc.getKeyFor(id));
        return userObj.orElseThrow(() -> new ApiException(ErrorType.USER_NOT_FOUND));
    }

    @Override
    public UserDoc create(UserDoc user) {
        // We first search by email, the user should not exist
        Optional<UserDoc> userObj = Optional.ofNullable(findByEmail(user.getEmail()));
        if (userObj.isPresent()) {
            throw new ApiException(ErrorType.USER_ALREADY_EXISTS);
        }
        user.setId(userCounterRepo.counter()); // internally we set the key with that id
        UserDoc userFromDb = userRepo.save(user);
        LOG.info("user key: {}", userFromDb.getKey());
        return userFromDb;
    }

    @Override
    public void update(Long id, UserDoc user) {
        // The user should exist
        UserDoc existingUser = findByIdOrThrow(id);
        if (!Util.isNullOrEmpty(user.getName())) {
            existingUser.setName(user.getName());
        }
        if (user.getNicknames() != null) {
            existingUser.setNicknames(user.getNicknames());
        }
        if (user.getAge() != null) {
            existingUser.setAge(user.getAge());
        }
        // Save
        userRepo.save(existingUser);
    }

    @Override
    public void delete(Long id) {
        // The user should exist
        findByIdOrThrow(id);
        userRepo.deleteById(UserDoc.getKeyFor(id));
    }

    @Override
    public Boolean exists(Long id) {
        return userRepo.existsById(UserDoc.getKeyFor(id));
    }

    @Override
    public List<UserDoc> findUsersByNickname(String nickname) {
        return userRepo.findUsersWithNickname(nickname);
    }

    @Override
    public List<UserBasicDoc> findUsersByName(String name) {
        final String cleanName = name.toLowerCase().trim();
        return userRepo.findUsersWithName(cleanName);
    }

    @Override
    public List<UserDoc> findAll() {
        return userRepo.findAllUsers();
    }

    @Override
    public Integer countAll() {
        return userRepo.countAll();
    }

    @Override
    public List<UserDoc> deleteUsersByAge(Integer age) {
        List<UserDoc> userDocs = userRepo.deleteUsersWithAge(age);
        LOG.info("size: {}", userDocs.size());
        return userDocs;
    }

    // TODO move this method to a repo class
    @Override
    public List<UserBasicDoc> findUsersbyNameUsingTemplateN1QLProjectionWithCoverIndex(String name) {
        final String cleanName = name.toLowerCase().trim();
        // NOTE: This method uses raw N1QL query that projects 1 attribute and it is a covered index
        // ('idx_user_find_by_name')
        String queryStr =
                "SELECT u.name FROM users u WHERE u.type = 'com.wedevol.springdatacouchbase.core.dao.doc.UserDoc' AND LOWER(u.name) LIKE '%' || $name || '%'";
        JsonObject placeholderValues = JsonObject.create().put("name", cleanName);
        N1qlParams n1qlParams = N1qlParams.build().pretty(false); // TODO add more configurations
        List<UserBasicDoc> userDocs = defaultTemplate.findByN1QLProjection(
                N1qlQuery.parameterized(queryStr, placeholderValues, n1qlParams), UserBasicDoc.class);
        LOG.info("size: {}", userDocs.size());
        return userDocs;
    }

    // TODO move this method to a repo class
    @Override
    public List<Long> findAllUserIdsUsingTemplateN1ql() {
        // NOTE This method uses raw N1QL query that projects 1 attribute
        String queryStr =
                "SELECT u.id AS userId FROM users u WHERE u.type = 'com.wedevol.springdatacouchbase.core.dao.doc.UserDoc'";
        N1qlQueryResult result = defaultTemplate.queryN1QL(N1qlQuery.simple(queryStr));
        if (!result.errors().isEmpty()) {
            LOG.error("Error running query findAllUserIdsUsingTemplateN1ql()");
            return Collections.emptyList();
        }
        List<Long> userIds =
                result.allRows().stream().map(row -> row.value().getLong("userId")).collect(Collectors.toList());
        LOG.info("size: {}", userIds.size());
        return userIds;
    }

    // TODO move this method to a repo class
    @Override
    public List<UserDoc> findUsersUsingUseKeys(List<Long> userIds) {
        // NOTE the keys are constructed based on the ids
        List<String> userKeys = userIds.stream().map(UserDoc::getKeyFor).collect(Collectors.toList());
        // NOTE This method uses raw N1QL query that projects the complete entity
        String queryStr = "SELECT u.*, META(u).id AS _ID, META(u).cas AS _CAS FROM users u USE KEYS $userIdKeys";
        JsonObject placeholderValues = JsonObject.create().put("userIdKeys", JsonArray.from(userKeys));
        N1qlParams n1qlParams = N1qlParams.build().pretty(false); // TODO add more configurations
        List<UserDoc> userDocs = defaultTemplate
                .findByN1QL(N1qlQuery.parameterized(queryStr, placeholderValues, n1qlParams), UserDoc.class);
        LOG.info("size: {}", userDocs.size());
        return userDocs;
    }

}
