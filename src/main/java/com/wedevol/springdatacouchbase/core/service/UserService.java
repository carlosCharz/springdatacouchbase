package com.wedevol.springdatacouchbase.core.service;

import com.wedevol.springdatacouchbase.core.dao.doc.UserDoc;

/**
 * Interface for the User Service Implementation
 *
 * @author Charz++
 */

public interface UserService {

	UserDoc findByUserId(Long userId);
	
	UserDoc findOne(String id);

}
