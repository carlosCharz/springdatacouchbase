package com.wedevol.springdatacouchbase.core.configuration;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.query.Consistency;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.wedevol.springdatacouchbase.core.dao.doc.PhoneDoc;
import com.wedevol.springdatacouchbase.core.dao.doc.PlaceDoc;

/**
 * Class that connects to Couchbase
 *
 * @author Charz++
 */

@Configuration
@EnableCouchbaseRepositories(basePackages = {"com.wedevol.springdatacouchbase.core.dao"})
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(CouchbaseConfig.class);

    @Autowired
    private CouchbaseSetting couchbaseSetting;

    @Override
    protected List<String> getBootstrapHosts() {
        LOG.info("Registering host '{}' for couchbase cluster", couchbaseSetting.getHostName());
        return Arrays.asList(couchbaseSetting.getHostName());
    }

    @Override
    protected String getBucketName() {
        LOG.info("Opening bucket '{}'", couchbaseSetting.getBucketName());
        return couchbaseSetting.getBucketName();
    }

    @Override
    protected String getBucketPassword() {
        LOG.info("Get bucket password '{}'", couchbaseSetting.getPassword());
        return couchbaseSetting.getPassword();
    }

    @Override
    protected CouchbaseEnvironment getEnvironment() {
        DefaultCouchbaseEnvironment.builder().connectTimeout(60000) // by default 5 sec (5000 ms)
                .queryTimeout(20000) // by default 75 sec (75000 ms)
                .socketConnectTimeout(45000); // by default 1 sec (1000 ms)
        return super.getEnvironment();
    }

    @Override
    public Consistency getDefaultConsistency() {
        // By default, READ_YOUR_OWN_WRITES
        // Values: READ_YOUR_OWN_WRITES, STRONGLY_CONSISTENT, UPDATE_AFTER, EVENTUALLY_CONSISTENT
        return Consistency.READ_YOUR_OWN_WRITES;
    }

    @Override
    public String typeKey() {
        // By default, this attribute is named "_class".
        // Spring Data automatically adds to each document an attribute containing the full class name of the entity.
        // This field is the one used by N1QL queries to filter only documents corresponding to the repository’s entity.
        return "type";
    }

    /************
     * This is additional configuration if we want some other objects (PlaceDoc, PhoneDoc) to be stored in other bucket
     ************/

    @Bean(name = "placeBucket") // this is to differentiate with the default beans
    public Bucket placeBucket() throws Exception {
        return couchbaseCluster().openBucket("places", "123456abC"); // TODO you can get values from properties
    }

    @Bean(name = "placeBucketTemplate") // this is to differentiate with the default beans
    public CouchbaseTemplate placeTemplate() throws Exception {
        CouchbaseTemplate template = new CouchbaseTemplate(couchbaseClusterInfo(), // reuse the default bean
                placeBucket(), // the bucket is non-default
                mappingCouchbaseConverter(), translationService() // default beans here as well
        );
        template.setDefaultConsistency(getDefaultConsistency());
        return template;
    }

    @Override
    public void configureRepositoryOperationsMapping(RepositoryOperationsMapping baseMapping) {
        try {
            baseMapping // this is already using couchbaseTemplate as default
                    .mapEntity(PlaceDoc.class, placeTemplate()).mapEntity(PhoneDoc.class, placeTemplate());
            // every repository dealing with Place will be backed by placeTemplate()
        } catch (Exception e) {
            throw new RuntimeException("Place bucket could not be configured properly!");
        }
    }
}
