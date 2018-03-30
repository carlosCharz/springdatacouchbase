package com.wedevol.springdatacouchbase.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Entry Point class
 * 
 * https://docs.spring.io/spring-boot/docs/current/reference/html/howto-traditional-deployment.html
 *
 * @author Charz++
 */

@SpringBootApplication
public class EntryPoint extends SpringBootServletInitializer {

	protected static final Logger logger = LoggerFactory.getLogger(EntryPoint.class);

	public static void main(String[] args) {
		logger.info("Initializing Spring Boot!");
		SpringApplication.run(EntryPoint.class, args);
	}

}
