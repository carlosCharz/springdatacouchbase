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
import com.wedevol.springdatacouchbase.core.dao.doc.PhoneDoc;
import com.wedevol.springdatacouchbase.core.service.PhoneService;

/**
 * Phone REST Controller
 *
 * @author charz
 *
 */
@RestController
@RequestMapping("/phones")
public class PhoneController {

    private static final Logger logger = LoggerFactory.getLogger(PhoneController.class);

    @Autowired
    private PhoneService phoneService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PhoneDoc findById(@PathVariable String id) {
        logger.info("Find phone by id: {}", id);
        return phoneService.findById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    // TODO it is not a good practice to expose the DB entity (the doc) in the request. This is just for the example.
    public void create(@Valid @RequestBody PhoneDoc phone) {
        logger.info("Create phone");
        phoneService.create(phone);
    }

}
