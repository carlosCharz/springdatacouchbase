package com.wedevol.springdatacouchbase.core.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.couchbase.config.BeanNames;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;
import com.wedevol.springdatacouchbase.core.dao.doc.CarDoc;
import com.wedevol.springdatacouchbase.core.exception.ApiException;
import com.wedevol.springdatacouchbase.core.exception.ErrorType;
import com.wedevol.springdatacouchbase.core.service.CarService;

/**
 * Service that manages the creation of a Car to exemplify the use of doc attributes with automatic prefix and suffix
 * for key generation and the usage of the template methods (not the CRUD repository).
 *
 * @author Charz++
 */

@Service
public class CarServiceImpl implements CarService {

    private static final Logger LOG = LoggerFactory.getLogger(CarServiceImpl.class);

    // NOTE add the qualifier in case you have multiple buckets in your configuration otherwise remove it
    @Autowired
    @Qualifier(BeanNames.COUCHBASE_TEMPLATE)
    private CouchbaseTemplate defaultTemplate;

    @Override
    public CarDoc findByKeyOrThrow(Long number, String manufacturer) {
        CarDoc carDoc = new CarDoc(number, manufacturer);
        Optional<CarDoc> carObj =
                Optional.ofNullable(defaultTemplate.findById(defaultTemplate.getGeneratedId(carDoc), CarDoc.class));
        return carObj.orElseThrow(() -> new ApiException(ErrorType.CAR_NOT_FOUND));
    }

    @Override
    public void create(CarDoc car) {
        defaultTemplate.insert(car);
        LOG.info("car key: {}", car.getKey());
    }

}
