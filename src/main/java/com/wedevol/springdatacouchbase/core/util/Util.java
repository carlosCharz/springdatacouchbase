package com.wedevol.springdatacouchbase.core.util;

import java.util.List;

/**
 * Util class for constants and generic methods
 * 
 * @author Charz++
 */

public class Util {

	public static boolean isNullOrEmpty(String param) {
		return param == null || param.trim().length() == 0;
	}
	
	public static boolean isNullOrEmpty(List<?> element){
		return element == null || element.isEmpty();
	}

}
