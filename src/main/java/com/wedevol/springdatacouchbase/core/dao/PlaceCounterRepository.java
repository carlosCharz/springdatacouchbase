package com.wedevol.springdatacouchbase.core.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.couchbase.client.java.Bucket;

/**
 * Place counter repository
 *
 * @author Charz++
 */

@Repository
public class PlaceCounterRepository {

    private static final long INITIAL_COUNTER_VALUE = 1;
    private static final String PLACE_COUNTER_KEY = "place::counter";

    // NOTE: add the qualifier in case you have multiple buckets in your configuration otherwise remove it
    @Autowired
    @Qualifier("placeBucket")
    private Bucket bucket;

    public Long counter() {
        return bucket.counter(PLACE_COUNTER_KEY, 1, INITIAL_COUNTER_VALUE).content();
    }

    public void inc() {
        bucket.counter(PLACE_COUNTER_KEY, 1, INITIAL_COUNTER_VALUE);
    }

    public void dec() {
        bucket.counter(PLACE_COUNTER_KEY, -1, INITIAL_COUNTER_VALUE);
    }

    public Long getValue() {
        return bucket.counter(PLACE_COUNTER_KEY, 0).content();
    }

}
