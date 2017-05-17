package com.wedevol.springdatacouchbase.core.util;

/**
 * Util class for constants and generic methods
 * 
 * @author Charz++
 */

public class Util {

	public static final String USER_KEY_PREFIX = "user::";

	public static boolean isNullOrEmpty(String param) {
		return param == null || param.trim()
										.length() == 0;
	}

}
