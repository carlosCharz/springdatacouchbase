package com.wedevol.springdatacouchbase.core.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.wedevol.springdatacouchbase.core.dao.doc.UserBasicDoc;
import com.wedevol.springdatacouchbase.core.dao.doc.UserDoc;
import com.wedevol.springdatacouchbase.core.service.UserService;

/**
 * User REST Controller
 *
 * @author charz
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UserDoc findById(@PathVariable Long userId) {
        LOG.info("Find user by id: {}", userId);
        return userService.findByIdOrThrow(userId);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    // TODO it is not a good practice to expose the DB entity (the doc) in the request and the response. This is just
    // for the example.
    public UserDoc create(@Valid @RequestBody UserDoc user) {
        LOG.info("Create user");
        return userService.create(user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    // TODO it is not a good practice to expose the DB entity (the doc) in the request. This is just for the example.
    public void update(@PathVariable Long userId, @Valid @RequestBody UserDoc user) {
        LOG.info("Update user: {}", userId);
        userService.update(userId, user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId) {
        LOG.info("Delete user: {}", userId);
        userService.delete(userId);
    }

    @RequestMapping(value = "/{userId}/exists", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Boolean exists(@PathVariable Long userId) {
        LOG.info("Exists user {}?", userId);
        return userService.exists(userId);
    }

    @RequestMapping(value = "/find/email", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UserDoc findByEmail(@RequestParam(value = "email") String email) {
        LOG.info("Find user by email: {}", email);
        return userService.findByEmail(email);
    }

    @RequestMapping(value = "/find/nickname", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<UserDoc> findUsersByNickname(@RequestParam(value = "nickname") String nickname) {
        LOG.info("Find users by nickname: {}", nickname);
        return userService.findUsersByNickname(nickname);
    }

    @RequestMapping(value = "/find/name", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<UserBasicDoc> findUsersByName(@RequestParam(value = "name") String name) {
        LOG.info("Find users by name: {}", name);
        return userService.findUsersByName(name);
    }

    @RequestMapping(value = "/find/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<UserDoc> findAll() {
        LOG.info("Find all users");
        return userService.findAll();
    }

    @RequestMapping(value = "/count/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Integer countAll() {
        LOG.info("Count all users");
        return userService.countAll();
    }

    @RequestMapping(value = "/delete/age", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public List<UserDoc> deleteUsersByAge(@RequestParam(value = "age") Integer age) {
        LOG.info("Delete users by age: {}", age);
        return userService.deleteUsersByAge(age);
    }

    @RequestMapping(value = "/find/name/coverindex", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<UserBasicDoc> findUsersbyNameUsingTemplateN1QLProjectionWithCoverIndex(
            @RequestParam(value = "name") String name) {
        LOG.info("Find users by name using template N1ql projection with cover index: {}", name);
        return userService.findUsersbyNameUsingTemplateN1QLProjectionWithCoverIndex(name);
    }

    @RequestMapping(value = "/find/ids", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Long> findAllUserIdsUsingTemplateN1ql() {
        LOG.info("Find all user ids with template N1ql");
        return userService.findAllUserIdsUsingTemplateN1ql();
    }

    @RequestMapping(value = "/find/usekeys", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<UserDoc> findUsersUsingUseKeys(@RequestParam(value = "ids") List<Long> userIds) {
        LOG.info("Find users by keys using template projection N1ql: {}",
                userIds.stream().map(id -> Long.toString(id)).collect(Collectors.joining(",")));
        return userService.findUsersUsingUseKeys(userIds);
    }
}
