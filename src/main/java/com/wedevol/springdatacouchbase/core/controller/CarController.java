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
import com.wedevol.springdatacouchbase.core.dao.doc.CarDoc;
import com.wedevol.springdatacouchbase.core.service.CarService;

/**
 * Car REST Controller
 *
 * @author charz
 *
 */
@RestController
@RequestMapping("/cars")
public class CarController {

    private static final Logger LOG = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/{number}/{manufacturer}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public CarDoc findByKey(@PathVariable Long number, @PathVariable String manufacturer) {
        LOG.info("Find car by key: {}-{}", number, manufacturer);
        return carService.findByKeyOrThrow(number, manufacturer);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    // TODO it is not a good practice to expose the DB entity (the doc) in the request. This is just for the example.
    public void create(@Valid @RequestBody CarDoc car) {
        LOG.info("Create car");
        carService.create(car);
    }

}
