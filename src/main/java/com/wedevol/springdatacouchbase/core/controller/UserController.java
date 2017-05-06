package com.wedevol.springdatacouchbase.core.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wedevol.springdatacouchbase.core.server.CcsConnection;
import com.wedevol.springdatacouchbase.core.server.bean.CcsOutMessage;
import com.wedevol.springdatacouchbase.core.server.mapper.MessageMapper;
import com.wedevol.springdatacouchbase.core.util.Util;

/**
 * User REST Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private CcsConnection ccsConnection;

	@RequestMapping(value = "/tokendevice/{token}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void testNotification(@PathVariable("token") String token) {
		logger.info("Controller -> test notification");

		// Create test message
		final String message = "Hello from Notifier! This is a test message!";
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("message", message);
		final String jsonContent = MessageMapper.toJsonString(jsonMap);
		Map<String, String> dataPayload = new HashMap<String, String>();
		dataPayload.put("action", "MESSAGE");
		dataPayload.put("senderId", "1");
		dataPayload.put("receiverId", "1");
		dataPayload.put("type", "CHAT");
		dataPayload.put("content", jsonContent);
		Map<String, String> notificationPayload = new HashMap<String, String>();
		notificationPayload.put("body", message);
		notificationPayload.put("badge", "1");
		notificationPayload.put("click_action", "CHAT");
		notificationPayload.put("sound", "default");
		final String messageId = Util.getUniqueMessageId();
		final CcsOutMessage outMessage = new CcsOutMessage(token, messageId, 259200, dataPayload, notificationPayload,
				"high");

		// Create json request
		final String jsonRequest = MessageMapper.toJsonString(outMessage);
		ccsConnection.sendPacket(jsonRequest);
	}


}
