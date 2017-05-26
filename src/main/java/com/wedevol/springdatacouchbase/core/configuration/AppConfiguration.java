package com.wedevol.springdatacouchbase.core.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Application configuration class
 *
 * @author Charz++
 */

@EnableConfigurationProperties({CouchbaseSetting.class /*other setting classes*/})
public class AppConfiguration {

}

