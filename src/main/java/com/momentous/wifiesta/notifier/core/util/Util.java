package com.momentous.wifiesta.notifier.core.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

/**
 * Util class for constants and generic methods
 * 
 * @author Charz++
 */

public class Util {

	// For the FCM connection
	public static final String FCM_ELEMENT_NAME = "gcm";
	public static final String FCM_NAMESPACE = "google:mobile:data";
	public static final String FCM_SERVER_CONNECTION = "gcm.googleapis.com";

	// For the backend action attribute values
	public static final String BACKEND_ACTION_ECHO = "ECHO";
	public static final String BACKEND_ACTION_MESSAGE = "MESSAGE";

	// For the app common payload message attributes (android - xmpp server)
	public static final String PAYLOAD_ATTRIBUTE_ACTION = "action";
	public static final String PAYLOAD_ATTRIBUTE_SENDER_ID = "senderId";
	public static final String PAYLOAD_ATTRIBUTE_RECEIVER_ID = "receiverId";
	public static final String PAYLOAD_ATTRIBUTE_TYPE = "type";
	public static final String PAYLOAD_ATTRIBUTE_TIME_STAMP = "timeStamp";
	public static final String PAYLOAD_ATTRIBUTE_CONTENT = "content";
	public static final String PAYLOAD_ATTRIBUTE_MESSAGE = "message";

	/**
	 * Returns a random message id to uniquely identify a message
	 */
	public static String getUniqueMessageId() {
		final LocalDateTime now = LocalDateTime.now();
		final String format = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.ENGLISH));
		final UUID randomUUID = UUID.randomUUID();
		final String messageId = new StringBuilder().append("m-")
													.append(format)
													.append("-")
													.append(randomUUID.toString())
													.toString();
		return messageId;
	}

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

	public static String createFcmServerUsername(Long fcmSenderId) {
		return new StringBuilder().append(fcmSenderId)
									.append("@")
									.append(FCM_SERVER_CONNECTION)
									.toString();
	}
}
