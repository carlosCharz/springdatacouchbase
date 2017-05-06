package com.wedevol.springdatacouchbase.core.configuration;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Couchbase settings loaded from a property file
 * 
 * @author Charz++
 */

@Component
@ConfigurationProperties(prefix = "couchbase")
public class CouchbaseSetting {

	protected static final Logger logger = LoggerFactory.getLogger(CouchbaseSetting.class);

	private String hostName;

	private String bucketName;

	private String password;

	public CouchbaseSetting() {
		logger.info("Loading Couchbase properties");
	}

	@PostConstruct
	public void postConstruct() {
		logger.info("Couchbase properties -> hostName: '{}', bucketName: '{}', password: '{}'", hostName, bucketName,
				password);
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
