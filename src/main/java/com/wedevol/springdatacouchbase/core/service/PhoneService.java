package com.wedevol.springdatacouchbase.core.service;

import com.wedevol.springdatacouchbase.core.dao.doc.PhoneDoc;

/**
 * Interface for the Phone Service Implementation
 *
 * @author Charz++
 */

public interface PhoneService {

    PhoneDoc findByIdOrThrow(String id);

    PhoneDoc create(PhoneDoc phone);
}
