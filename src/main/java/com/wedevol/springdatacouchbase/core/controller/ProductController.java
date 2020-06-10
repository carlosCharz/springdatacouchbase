package com.wedevol.springdatacouchbase.core.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.wedevol.springdatacouchbase.core.dao.doc.ProductDoc;
import com.wedevol.springdatacouchbase.core.service.ProductService;

/**
 * Product REST Controller
 *
 * @author charz
 *
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ProductDoc findById(@PathVariable String id) {
        LOG.info("Find product by id: {}", id);
        return productService.findByIdOrThrow(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    // TODO it is not a good practice to expose the DB entity (the doc) in the request. This is just for the example.
    public void create(@Valid @RequestBody ProductDoc product) {
        LOG.info("Create product");
        productService.create(product);
    }

}
