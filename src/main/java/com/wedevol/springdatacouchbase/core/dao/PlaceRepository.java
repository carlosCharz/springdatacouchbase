package com.wedevol.springdatacouchbase.core.dao;

import org.springframework.data.repository.CrudRepository;
import com.wedevol.springdatacouchbase.core.dao.doc.PlaceDoc;

/**
 * Standard CRUD repository for Place doc + query methods
 *
 * @author Charz++
 */

public interface PlaceRepository extends CrudRepository<PlaceDoc, String> {

}
