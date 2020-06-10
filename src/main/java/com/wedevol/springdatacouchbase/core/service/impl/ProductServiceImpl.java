package com.wedevol.springdatacouchbase.core.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.couchbase.config.BeanNames;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;
import com.wedevol.springdatacouchbase.core.dao.doc.ProductDoc;
import com.wedevol.springdatacouchbase.core.exception.ApiException;
import com.wedevol.springdatacouchbase.core.exception.ErrorType;
import com.wedevol.springdatacouchbase.core.service.ProductService;

/**
 * Service that manages the creation of a Product to exemplify the use of unique number (Couchbase UUID) for key
 * generation and the usage of the template methods (not the CRUD repository). NOTE: As of 2019 I could not save the
 * auto-generated unique Couchbase key inside the doc.
 *
 * @author Charz++
 */

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    // NOTE add the qualifier in case you have multiple buckets in your configuration otherwise remove it
    @Autowired
    @Qualifier(BeanNames.COUCHBASE_TEMPLATE)
    private CouchbaseTemplate defaultTemplate;

    @Override
    public ProductDoc findByIdOrThrow(String id) { // this is the key
        Optional<ProductDoc> productObj = Optional.ofNullable(defaultTemplate.findById(id, ProductDoc.class));
        return productObj.orElseThrow(() -> new ApiException(ErrorType.PRODUCT_NOT_FOUND));
    }

    @Override
    public void create(ProductDoc product) {
        defaultTemplate.insert(product);
        LOG.info("product key: {}", product.getId());
    }

}
