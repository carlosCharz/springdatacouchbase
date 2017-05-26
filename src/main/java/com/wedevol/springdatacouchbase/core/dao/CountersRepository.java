package com.wedevol.springdatacouchbase.core.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.couchbase.client.java.Bucket;

/**
 * User counter repository
 *
 * @author Charz++
 */

@Repository
public class CountersRepository {

    private static final long INITIAL_COUNTER_VALUE = 1;

    @Autowired
    private Bucket bucket;
    
    public Long counter(final String counterKey) {
        return bucket.counter(counterKey, 1, INITIAL_COUNTER_VALUE).content();
    }

    public void incCounter(final String counterKey) {
        bucket.counter(counterKey, 1, INITIAL_COUNTER_VALUE);
    }

    public void decCounter(final String counterKey) {
        bucket.counter(counterKey, -1, INITIAL_COUNTER_VALUE);
    }

    public Long getCounterValue(final String counterKey) {
        return bucket.counter(counterKey, 0).content();
    }

}