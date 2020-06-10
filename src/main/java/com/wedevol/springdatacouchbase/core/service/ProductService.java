package com.wedevol.springdatacouchbase.core.service;

import com.wedevol.springdatacouchbase.core.dao.doc.ProductDoc;

/**
 * Interface for the Product Service Implementation
 *
 * @author Charz++
 */

public interface ProductService {

    ProductDoc findByIdOrThrow(String id);

    void create(ProductDoc product);
}
