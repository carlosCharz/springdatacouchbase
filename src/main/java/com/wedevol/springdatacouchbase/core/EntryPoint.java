package com.wedevol.springdatacouchbase.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry Point class
 *
 * https://docs.spring.io/spring-boot/docs/current/reference/html/howto-traditional-deployment.html
 *
 * @author Charz++
 */

@SpringBootApplication
public class EntryPoint {

    public static void main(String[] args) {
        SpringApplication.run(EntryPoint.class, args);
    }

}
