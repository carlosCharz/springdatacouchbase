package com.wedevol.springdatacouchbase.core.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;
import com.wedevol.springdatacouchbase.core.dao.doc.ProductDoc;
import com.wedevol.springdatacouchbase.core.exception.ApiException;
import com.wedevol.springdatacouchbase.core.exception.ErrorType;
import com.wedevol.springdatacouchbase.core.service.ProductService;

/**
 * Service that manages the creation of a Product to exemplify the use of unique number (uuid) for
 * key generation.
 *
 * @author Charz++
 */

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private CouchbaseTemplate template;

  @Override
  public ProductDoc findById(String id) {
    final Optional<ProductDoc> productObj = Optional.ofNullable(template.findById(id, ProductDoc.class));
    return productObj.orElseThrow(() -> new ApiException(ErrorType.PRODUCT_NOT_FOUND));
  }

  @Override
  public void create(ProductDoc product) {
    template.insert(product);
  }

}
