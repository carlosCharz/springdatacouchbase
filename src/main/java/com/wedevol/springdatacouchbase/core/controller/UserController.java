package com.wedevol.springdatacouchbase.core.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wedevol.springdatacouchbase.core.dao.doc.UserDoc;
import com.wedevol.springdatacouchbase.core.service.UserService;

/**
 * User REST Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

	protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<UserDoc> findAll() {
		return userService.findAll();
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public UserDoc findById(@PathVariable Long userId) {
		return userService.findById(userId);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public UserDoc create(@Valid @RequestBody UserDoc user) {
		return userService.create(user);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long userId, @Valid @RequestBody UserDoc user) {
		userService.update(userId, user);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long userId) {
		userService.delete(userId);
	}
	
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Long count() {
		return userService.count();
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Boolean exists(@PathVariable Long userId) {
		return userService.exists(userId);
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public UserDoc findByEmail(@RequestParam(value = "email") String email) {
		logger.info("Find user by email: " + email);
		return userService.findByEmail(email);
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<UserDoc> findUsersByNickname(@RequestParam(value = "nickname") String nickname) {
		logger.info("Find users by nickname: " + nickname);
		return userService.findUsersByNickname(nickname);
	}

	

}
