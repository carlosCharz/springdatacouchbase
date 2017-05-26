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

	// To use N1QL we should at least create a primary NQ1L index or to be more specific, a N1QL secondary indexes tailored for queries for better performance
	// CREATE PRIMARY INDEX `users-primary-index` ON `users` USING GSI;
	// DROP INDEX `users`.`users-primary-index` USING GSI;
	UserDoc findByEmail(String email);
	
	@Query("#{#n1ql.selectEntity} WHERE type='user' AND #{#n1ql.filter} AND ARRAY_LENGTH(nicknames) > 0 AND ANY nick IN nicknames SATISFIES nick = $1 END")
	List<UserDoc> findUsersWithNickname(String nickname);

}
