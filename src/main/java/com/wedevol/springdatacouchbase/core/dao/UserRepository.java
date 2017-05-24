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

	UserDoc findByEmail(String email);
	
	@Query("#{#n1ql.selectEntity} WHERE type='user' AND #{#n1ql.filter} AND ARRAY_LENGTH(nicknames) > 0 AND ANY nick IN nicknames SATISFIES nick = $1 END")
	List<UserDoc> findUsersWithNickname(String nickname);

}
