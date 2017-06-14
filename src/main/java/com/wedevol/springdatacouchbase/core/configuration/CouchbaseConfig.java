package com.wedevol.springdatacouchbase.core.configuration;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

/**
 * Class that connects to Couchbase
 *
 * @author Charz++
 */

@Configuration
@EnableCouchbaseRepositories(basePackages = { "com.wedevol.springdatacouchbase.core.dao" })
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

	protected static final Logger logger = LoggerFactory.getLogger(CouchbaseConfig.class);

	@Autowired
	private CouchbaseSetting couchbaseSetting;

	@Override
	protected List<String> getBootstrapHosts() {
		logger.info("Registering host '{}' for couchbase cluster", couchbaseSetting.getHostName());
		return Arrays.asList(couchbaseSetting.getHostName());
	}

	@Override
	protected String getBucketName() {
		logger.info("Opening bucket '{}'", couchbaseSetting.getBucketName());
		return couchbaseSetting.getBucketName();
	}

	@Override
	protected String getBucketPassword() {
		return couchbaseSetting.getPassword();
	}
	
	@Override
	public String typeKey() {
		// By default, this attribute is named "_class". 
		// Spring Data automatically adds to each document an attribute containing the full class name of the entity.
		// This field is the one used by N1QL queries to filter only documents corresponding to the repository’s entity.
		return "type";
	}
}
