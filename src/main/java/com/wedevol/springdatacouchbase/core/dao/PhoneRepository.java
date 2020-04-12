package com.wedevol.springdatacouchbase.core.dao;

import org.springframework.data.repository.CrudRepository;
import com.wedevol.springdatacouchbase.core.dao.doc.PhoneDoc;

/**
 * Standard CRUD repository for Phone doc + query methods
 *
 * @author Charz++
 */

public interface PhoneRepository extends CrudRepository<PhoneDoc, String> {

}
