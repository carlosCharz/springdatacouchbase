package com.wedevol.springdatacouchbase.core.service;

import com.wedevol.springdatacouchbase.core.dao.doc.PlaceDoc;

/**
 * Interface for the Place Service Implementation
 *
 * @author Charz++
 */

public interface PlaceService {

  PlaceDoc findById(Long id);

  PlaceDoc create(PlaceDoc place);
}
