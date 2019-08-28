package com.wedevol.springdatacouchbase.core.dao;

import java.util.List;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.wedevol.springdatacouchbase.core.dao.doc.UserBasicDoc;
import com.wedevol.springdatacouchbase.core.dao.doc.UserDoc;

/**
 * Standard CRUD repository for User doc + query methods
 * 
 * Note:
 * 
 * 1. To use N1QL we should at least create a primary NQ1L index or, to be more specific, a N1QL
 * secondary indexes tailored for queries for better performance. 2. Workaround for raw N1QL
 * queries: add "META(c).id as _ID, META(c).cas as _CAS" to the select
 *
 * @author Charz++
 */

public interface UserRepository extends CrudRepository<UserDoc, String> {

  // This method is a query method defined in the interface. In addition to query methods, query
  // derivation for both count and delete queries, is available.
  UserDoc findByEmail(String email);

  // This method uses the Query annotation to provide a N1QL statement inline. A few N1QL-specific
  // values are provided through SpEL.
  @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND ARRAY_LENGTH(nicknames) > 0 AND ANY nick IN nicknames SATISFIES nick = $1 END")
  List<UserDoc> findUsersWithNickname(String nickname);

  @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter}")
  List<UserDoc> findAllUsers();

  // We can use raw N1QL queries
  @Query("SELECT COUNT(u.id) AS c FROM users u WHERE u.type = 'com.wedevol.springdatacouchbase.core.dao.doc.UserDoc'")
  Integer countAll();

  // Raw N1QL query that projects some attributes (not all) using like operator and a parameterized
  // variable
  @Query("SELECT u.id, u.name, META(u).id AS _ID, META(u).cas AS _CAS FROM users u WHERE u.type = 'com.wedevol.springdatacouchbase.core.dao.doc.UserDoc' AND LOWER(u.name) LIKE '%' || $name || '%'")
  List<UserBasicDoc> findUsersWithName(@Param("name") String name);

}
