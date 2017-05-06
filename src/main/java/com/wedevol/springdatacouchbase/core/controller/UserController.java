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

import com.wedevol.springdatacouchbase.core.bean.User;
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
	public List<User> findAll() {
		return userService.findAll();
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public User findById(@PathVariable Long userId) {
		return userService.findById(userId);
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<User> findUsersByEmail(@RequestParam(value = "email") String email) {
		logger.info("Find users by email: " + email);
		return userService.findUsersByEmail(email);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public User create(@Valid @RequestBody User User) {
		return userService.create(user);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long userId, @Valid @RequestBody User User) {
		userService.update(userId, User);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long userId) {
		userService.delete(userId);
	}

}
