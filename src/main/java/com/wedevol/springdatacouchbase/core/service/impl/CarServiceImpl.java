package com.wedevol.springdatacouchbase.core.service.impl;

import java.util.Optional;
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
 * Service that manages the creation of a Car to exemplify the use of doc attributes for key
 * generation.
 *
 * @author Charz++
 */

@Service
public class CarServiceImpl implements CarService {

  // add the qualifier in case you have multiple buckets in your configuration otherwise remove it
  @Autowired
  @Qualifier(BeanNames.COUCHBASE_TEMPLATE)
  private CouchbaseTemplate template;

  @Override
  public CarDoc findByKey(Long number, String manufacturer) {
    final CarDoc carDoc = new CarDoc(number, manufacturer);
    final Optional<CarDoc> carObj =
        Optional.ofNullable(template.findById(template.getGeneratedId(carDoc), CarDoc.class));
    return carObj.orElseThrow(() -> new ApiException(ErrorType.CAR_NOT_FOUND));
  }

  @Override
  public void create(CarDoc car) {
    template.insert(car);
  }


}
