package com.wedevol.springdatacouchbase.core.service;

import java.util.List;

import com.wedevol.springdatacouchbase.core.dao.doc.UserDoc;

/**
 * Interface for the User Service Implementation
 *
 * @author Charz++
 */

public interface UserService {
	
	// CRUD repository interface
	
	List<UserDoc> findAll();
	
	UserDoc findById(Long id);

	UserDoc create(UserDoc user);

	void update(Long id, UserDoc user);

	void delete(Long id);
	
	Long count();
	
	Boolean exists(Long id);
	
	// Custom methods

	UserDoc findByEmail(String email);
	
	List<UserDoc> findUsersByNickname(String nickname);
	
	

}
