package com.wedevol.springdatacouchbase.core.util;

/**
 * Util class for constants and generic methods
 * 
 * @author Charz++
 */

public class Util {

	public static final String FCM_ELEMENT_NAME = "gcm";

	public static boolean isNullOrEmpty(String param) {
		return param == null || param.trim()
										.length() == 0;
	}

	public static boolean isEmpty(String param) {
		return param.trim()
					.length() == 0;
	}

	public static Long getCurrentUnixTime() {
		return System.currentTimeMillis() / 1000L;
	}

}
