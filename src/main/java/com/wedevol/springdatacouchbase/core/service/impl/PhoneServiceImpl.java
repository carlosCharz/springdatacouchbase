package com.wedevol.springdatacouchbase.core.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;
import com.wedevol.springdatacouchbase.core.dao.PhoneRepository;
import com.wedevol.springdatacouchbase.core.dao.doc.PhoneDoc;
import com.wedevol.springdatacouchbase.core.exception.ApiException;
import com.wedevol.springdatacouchbase.core.exception.ErrorType;
import com.wedevol.springdatacouchbase.core.service.PhoneService;
import com.wedevol.springdatacouchbase.core.util.Util;

/**
 * Service that manages the creation of a Phone to exemplify the use of multiple templates and repositories.
 *
 * @author Charz++
 */

@Service
public class PhoneServiceImpl implements PhoneService {

    private static final Logger LOG = LoggerFactory.getLogger(PhoneServiceImpl.class);

    @Autowired
    private PhoneRepository phoneRepo;

    // NOTE add the qualifier in case you have multiple buckets in your configuration otherwise remove it
    @Autowired
    @Qualifier("placeBucketTemplate")
    private CouchbaseTemplate placeBucketTemplate; // to get the key (they apply the prefix and suffix from the doc)

    @Override
    public PhoneDoc findByIdOrThrow(String id) {
        PhoneDoc phoneDoc = PhoneDoc.from(id);
        Optional<PhoneDoc> phoneObj = phoneRepo.findById(placeBucketTemplate.getGeneratedId(phoneDoc));
        return phoneObj.orElseThrow(() -> new ApiException(ErrorType.PHONE_NOT_FOUND));
    }

    @Override
    public PhoneDoc create(PhoneDoc phone) {
        phone.setId(Util.uuid());
        PhoneDoc phoneFromDb = phoneRepo.save(phone);
        LOG.info("phone key: {}", phoneFromDb.getKey());
        return phoneFromDb;
    }

}
