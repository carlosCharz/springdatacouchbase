package com.wedevol.springdatacouchbase.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry Point class
 *
 * @author Charz++
 */

@SpringBootApplication
public class EntryPoint {

	protected static final Logger logger = LoggerFactory.getLogger(EntryPoint.class);

	public static void main(String[] args) {
		SpringApplication.run(EntryPoint.class, args);
	}

}
