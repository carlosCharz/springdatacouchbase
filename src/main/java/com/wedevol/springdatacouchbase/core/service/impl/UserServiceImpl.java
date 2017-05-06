package com.wedevol.springdatacouchbase.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wedevol.springdatacouchbase.core.dao.UserRepository;
import com.wedevol.springdatacouchbase.core.dao.doc.UserDoc;
import com.wedevol.springdatacouchbase.core.exception.ErrorType;
import com.wedevol.springdatacouchbase.core.exception.ServerException;
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
	public UserDoc findByUserId(Long userId) {
		try {
			List<UserDoc> groupList = repo.findByUserId(userId);
			if (groupList == null || groupList.isEmpty()) {
				return null;
			} else if (groupList.size() > 1) {
				throw new ServerException(ErrorType.ILLEGAL_RESULT_LENGTH);
			} else {
				UserDoc group = groupList.get(0);
				logger.info("User found: {}", group);
				return group;
			}
		} catch (ServerException e) {
			logger.error("Server Error: {}", e.getMessage());
		}
		return null;
	}

	@Override
	public UserDoc findOne(String key) {
		return repo.findOne(key);
	}

}
