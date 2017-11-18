package com.wedevol.springdatacouchbase.core.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wedevol.springdatacouchbase.core.dao.UserCounterRepository;
import com.wedevol.springdatacouchbase.core.dao.UserRepository;
import com.wedevol.springdatacouchbase.core.dao.doc.UserDoc;
import com.wedevol.springdatacouchbase.core.exception.ApiException;
import com.wedevol.springdatacouchbase.core.exception.ErrorType;
import com.wedevol.springdatacouchbase.core.service.UserService;
import com.wedevol.springdatacouchbase.core.util.Util;

/**
 * Service that manages the valid operations over the user repository
 *
 * @author Charz++
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserCounterRepository userCounterRepo;

	@Override
	public UserDoc findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public UserDoc findById(Long id) {
		final Optional<UserDoc> userObj = Optional.ofNullable(userRepo.findOne(UserDoc.getKeyFor(id)));
		return userObj.orElseThrow(() -> new ApiException(ErrorType.USER_NOT_FOUND));
	}

	@Override
	public UserDoc create(UserDoc user) {
		// We first search by email, the user should not exist
		final Optional<UserDoc> userObj = Optional.ofNullable(this.findByEmail(user.getEmail()));
		if (userObj.isPresent()) {
			throw new ApiException(ErrorType.USER_ALREADY_EXISTS);
		}
		user.setId(userCounterRepo.counter()); // internally we set the key with that id
		return userRepo.save(user);
	}

	@Override
	public void update(Long id, UserDoc user) {
		// The user should exist
		final UserDoc existingUser = this.findById(id);
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
		this.findById(id);
		userRepo.delete(UserDoc.getKeyFor(id));
	}

	@Override
	public Boolean exists(Long id) {
		return userRepo.exists(UserDoc.getKeyFor(id));
	}

	@Override
	public List<UserDoc> findUsersByNickname(String nickname) {
		return userRepo.findUsersWithNickname(nickname);
	}

	@Override
	public List<UserDoc> findAll() {
		return userRepo.findAllUsers();
	}

	@Override
	public Integer countAll(){
		return userRepo.countAll();
	}
}
