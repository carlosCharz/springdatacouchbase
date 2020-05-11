package com.wedevol.springdatacouchbase.core.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.couchbase.config.BeanNames;
import org.springframework.stereotype.Repository;
import com.couchbase.client.java.Bucket;

/**
 * User counter repository
 *
 * @author Charz++
 */

@Repository
public class UserCounterRepository {

    private static final long INITIAL_COUNTER_VALUE = 1;
    private static final String USER_COUNTER_KEY = "user::counter";

    // NOTE: add the qualifier in case you have multiple buckets in your configuration otherwise remove it
    @Autowired
    @Qualifier(BeanNames.COUCHBASE_BUCKET)
    private Bucket bucket;

    public Long counter() {
        return bucket.counter(USER_COUNTER_KEY, 1, INITIAL_COUNTER_VALUE).content();
    }

    public void inc() {
        bucket.counter(USER_COUNTER_KEY, 1, INITIAL_COUNTER_VALUE);
    }

    public void dec() {
        bucket.counter(USER_COUNTER_KEY, -1, INITIAL_COUNTER_VALUE);
    }

    public Long getValue() {
        return bucket.counter(USER_COUNTER_KEY, 0).content();
    }

}
