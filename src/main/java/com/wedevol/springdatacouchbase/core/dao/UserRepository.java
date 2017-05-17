package com.wedevol.springdatacouchbase.core.dao;

import java.util.List;

import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.wedevol.springdatacouchbase.core.dao.doc.UserDoc;

/**
 * Standard CRUD repository for user doc + query methods
 *
 * @author Charz++
 */

public interface UserRepository extends CrudRepository<UserDoc, String> {

	// The docs are indexed by type
	@Query("#{#n1ql.selectEntity} WHERE type='user' and email = $1")
	List<UserDoc> findByEmail(String email);
	
}
