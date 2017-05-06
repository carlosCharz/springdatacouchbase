package com.momentous.wifiesta.notifier.core;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Entry Point class for the XMPP Server
 *
 * @author Charz++
 */

@SpringBootApplication
public class EntryPoint extends SpringBootServletInitializer {

	protected static final Logger logger = LoggerFactory.getLogger(EntryPoint.class);

	private static final String catalinaHome = System.getProperty("catalina.home");

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		logger.info("Catalina home OS variable: {}", catalinaHome);
		return application.sources(EntryPoint.class)
							.properties(getProperties());
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(EntryPoint.class).sources(EntryPoint.class)
														.properties(getProperties())
														.run(args);
	}

	static Properties getProperties() {
		Properties props = new Properties();
		props.put("spring.config.location", "file:" + catalinaHome + "/conf/application.properties");
		return props;
	}

}
