package com.wedevol.springdatacouchbase.core.util;

import java.util.List;
import java.util.Random;

/**
 * Util class for constants and generic methods
 * 
 * @author Charz++
 */

public class Util {

	public static final String USER_KEY_PREFIX = "user::";

	public static boolean isNullOrEmpty(String param) {
		return param == null || param.trim().length() == 0;
	}
	
	public static boolean isNullOrEmpty(List<?> element){
		return element == null || element.isEmpty();
	}

	public static Long getUniqueId() {
		final Random random = new Random(System.nanoTime());
		return random.nextLong();
	}

}
