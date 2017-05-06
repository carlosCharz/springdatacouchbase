package com.momentous.wifiesta.notifier.core.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Application configuration class
 *
 * @author Charz++
 */

@EnableConfigurationProperties({FcmSetting.class, CouchbaseSetting.class})
public class AppConfiguration {

}

