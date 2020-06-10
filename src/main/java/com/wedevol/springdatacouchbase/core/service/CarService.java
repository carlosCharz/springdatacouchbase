package com.wedevol.springdatacouchbase.core.service;

import com.wedevol.springdatacouchbase.core.dao.doc.CarDoc;

/**
 * Interface for the Car Service Implementation
 *
 * @author Charz++
 */

public interface CarService {

    CarDoc findByKeyOrThrow(Long number, String manufacturer);

    void create(CarDoc car);
}
