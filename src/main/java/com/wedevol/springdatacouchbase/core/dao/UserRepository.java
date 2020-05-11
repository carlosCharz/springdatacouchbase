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
 * NOTES:
 *
 * 1. To use N1QL we should at least create a primary NQ1L index or, to be more specific, a N1QL secondary indexes
 * tailored for queries for better performance.
 *
 * 2. Workaround for raw N1QL queries: add "META(c).id as _ID, META(c).cas as _CAS" to the select and delete. By
 * default, all the queries that Spring Data Couchbase generates SELECT META(`bucket`).id AS _ID, META(`bucket`).cas AS
 * _CAS, `bucket`.* FROM `bucket` WHERE
 *
 * 3. As of August 2019 there is no way to get List<String> as response. You need to project the entity partially.
 *
 * 4. As of August 2019 there is no way to make a cover index due to the fact that we need to always project the id and
 * the cas. The cas is making the query not covered.
 *
 * @author Charz++
 */

public interface UserRepository extends CrudRepository<UserDoc, String> {

    // This method is a query method defined in the interface. In addition to query methods, query derivation for both
    // count and delete queries, is available.
    UserDoc findByEmail(String email);

    // These methods use the Query annotation to provide a N1QL statement inline. A few N1QL-specific values are
    // provided
    // through SpEL.
    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND ARRAY_LENGTH(nicknames) > 0 AND ANY nick IN nicknames SATISFIES nick = $1 END")
    List<UserDoc> findUsersWithNickname(String nickname);

    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter}")
    List<UserDoc> findAllUsers();

    // This method uses raw N1QL query
    @Query("SELECT COUNT(u.id) AS c FROM users u WHERE u.type = 'com.wedevol.springdatacouchbase.core.dao.doc.UserDoc'")
    Integer countAll();

    // This method uses raw N1QL query that projects some attributes (not all) using like operator and a parameterized
    // variable
    @Query("SELECT u.id, u.name, META(u).id AS _ID, META(u).cas AS _CAS FROM users u WHERE u.type = 'com.wedevol.springdatacouchbase.core.dao.doc.UserDoc' AND LOWER(u.name) LIKE '%' || $name || '%'")
    List<UserBasicDoc> findUsersWithName(@Param("name") String name);

    // This method uses raw N1QL query to delete entities based on a condition (the returning is optional)
    @Query("DELETE FROM users u WHERE u.type = 'com.wedevol.springdatacouchbase.core.dao.doc.UserDoc' AND u.age < $age RETURNING u.*, META(u).id AS _ID, META(u).cas AS _CAS")
    List<UserDoc> deleteUsersWithAge(@Param("age") Integer age);

}
