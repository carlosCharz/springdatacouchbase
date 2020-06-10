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
import com.wedevol.springdatacouchbase.core.dao.doc.PlaceDoc;
import com.wedevol.springdatacouchbase.core.service.PlaceService;

/**
 * Place REST Controller
 *
 * @author charz
 *
 */
@RestController
@RequestMapping("/places")
public class PlaceController {

    private static final Logger LOG = LoggerFactory.getLogger(PlaceController.class);

    @Autowired
    private PlaceService placeService;

    @RequestMapping(value = "/{placeId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PlaceDoc findById(@PathVariable Long placeId) {
        LOG.info("Find place by id: {}", placeId);
        return placeService.findByIdOrThrow(placeId);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    // TODO it is not a good practice to expose the DB entity (the doc) in the request and the response. This is just
    // for
    // the example.
    public PlaceDoc create(@Valid @RequestBody PlaceDoc place) {
        LOG.info("Create place");
        return placeService.create(place);
    }

}
