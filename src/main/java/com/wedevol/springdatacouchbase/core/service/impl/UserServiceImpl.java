package com.wedevol.springdatacouchbase.core.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.wedevol.springdatacouchbase.core.dao.UserRepository;
import com.wedevol.springdatacouchbase.core.dao.doc.UserDoc;
import com.wedevol.springdatacouchbase.core.exception.ApiException;
import com.wedevol.springdatacouchbase.core.exception.ErrorType;
import com.wedevol.springdatacouchbase.core.service.UserService;

/**
 * Service that manages the valid operations over the user repository
 *
 * @author Charz++
 */

@Service
public class UserServiceImpl implements UserService {

	protected static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository repo;
	
	// TODO remove logs

	@Override
	public UserDoc findByEmail(String email) {
		//try {
			List<UserDoc> groupList = repo.findByEmail(email);
			if (groupList == null || groupList.isEmpty()) {
				return null;
			} else if (groupList.size() > 1) {
				//throw new ServerException(ErrorType.ILLEGAL_RESULT_LENGTH);
			} else {
				UserDoc group = groupList.get(0);
				logger.info("User found: {}", group);
				return group;
			}
		//} catch (ServerException e) {
		//	logger.error("Server Error: {}", e.getMessage());
		//}
		return null;
	}

	@Override
	public List<UserDoc> findAll() {
		final Iterable<UserDoc> instructorsIterator = repo.findAll();
		return Lists.newArrayList(instructorsIterator);
	}

	@Override
	public UserDoc findById(Long id) {
		final Optional<UserDoc> studentObj = Optional.ofNullable(repo.findOne(UserDoc.getKeyFor(id)));
		return studentObj.orElseThrow(() -> new ApiException(ErrorType.USER_NOT_FOUND));
	}

	@Override
	public UserDoc create(UserDoc user) {
		// We first search by email, the student should not exist
		final Optional<UserDoc> userObj = Optional.ofNullable(this.findByEmail(user.getEmail()));
		if (userObj.isPresent()) {
			throw new ApiException(ErrorType.USER_ALREADY_EXISTS);
		}
		return repo.save(user);
	}

	@Override
	public void update(Long id, UserDoc user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean exists(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDoc> findUsersByNickname(String nickname) {
		// TODO Auto-generated method stub
		return null;
	}

}
