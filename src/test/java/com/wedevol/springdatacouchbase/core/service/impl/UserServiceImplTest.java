package com.wedevol.springdatacouchbase.core.service.impl;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.wedevol.springdatacouchbase.core.dao.UserRepository;
import com.wedevol.springdatacouchbase.core.dao.doc.UserDoc;
import com.wedevol.springdatacouchbase.core.exception.ApiException;

/**
 * Test the User Service Implementation: test the service logic
 *
 * @author Charz++
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
	
	private static final Long USER_ONE_ID = 1L;
	private static final String USER_ONE_KEY = UserDoc.getKeyFor(USER_ONE_ID);

	@Mock
	private UserRepository repoMock;

	@InjectMocks
	private UserServiceImpl userService;

	private UserDoc userDoc;

	@Before
	public void init() {
		userDoc = new UserDoc();
		userDoc.setId(USER_ONE_ID);
		userDoc.setNicknames(Arrays.asList("charz", "carlito"));
		userDoc.setAge(26);
		userDoc.setEmail("carlos@yopmail.com");
	}
	
	@Test
	public void findOneAndUserExists() {
		// Data preparation
		Mockito.when(repoMock.findOne(USER_ONE_KEY)).thenReturn(userDoc);

		// Method call
		UserDoc user = userService.findById(USER_ONE_ID);

		// Verification
		Assert.assertNotNull(user);
		Mockito.verify(repoMock, Mockito.times(1)).findOne(Mockito.anyString());
		Mockito.verifyNoMoreInteractions(repoMock);
	}
	
	@Test(expected = ApiException.class)
	public void findOneAndUserIsNull() {
		// Data preparation
		Mockito.when(repoMock.findOne(USER_ONE_KEY)).thenReturn(null);

		// Method call
		UserDoc user = userService.findById(USER_ONE_ID);

		// Verification
		Assert.assertNull(user);
		Mockito.verify(repoMock, Mockito.times(1)).findOne(Mockito.anyString());
		Mockito.verifyNoMoreInteractions(repoMock);
	}
	
	@Test(expected = ApiException.class)
	public void createUserAndUserAlreadyExists() {
		// Data preparation
		Mockito.when(repoMock.findByEmail("carlos@yopmail.com")).thenReturn(userDoc);

		// Method call
		UserDoc user = userService.create(userDoc);

		// Verification
		Assert.assertNull(user);
		Mockito.verify(repoMock, Mockito.times(1)).findByEmail(Mockito.anyString());
		Mockito.verifyNoMoreInteractions(repoMock);
	}
	
	@Test(expected = ApiException.class)
	public void updateUserAndUserNotExists() {
		// Data preparation
		Mockito.when(repoMock.findByEmail("carlos@yopmail.com")).thenReturn(null);

		// Method call
		userService.update(USER_ONE_ID, userDoc);

		// Verification
		Mockito.verify(repoMock, Mockito.times(1)).findByEmail(Mockito.anyString());
		Mockito.verifyNoMoreInteractions(repoMock);
	}
	
	@Test
	public void findUsersByNicknameAndUsersExist() {
		// Data preparation
		List<UserDoc> users = Arrays.asList(userDoc, userDoc, userDoc);
		Mockito.when(repoMock.findUsersWithNickname(Mockito.anyString())).thenReturn(users);

		// Method call
		List<UserDoc> userList = userService.findUsersByNickname("charz");

		// Verification
		Assert.assertThat(userList, Matchers.hasSize(3));
		Mockito.verify(repoMock, Mockito.times(1)).findUsersWithNickname(Mockito.anyString());
		Mockito.verifyNoMoreInteractions(repoMock);
	}

	@Test
	public void findAllUsers() {
		// Data preparation
		List<UserDoc> users = Arrays.asList(userDoc, userDoc, userDoc);
		Mockito.when(repoMock.findAllUsers()).thenReturn(users);

		// Method call
		List<UserDoc> userList = userService.findAll();

		// Verification
		Assert.assertThat(userList, Matchers.hasSize(3));
		Mockito.verify(repoMock, Mockito.times(1)).findAllUsers();
		Mockito.verifyNoMoreInteractions(repoMock);
	}

	@Test
	public void countAllUsers() {
		// Data preparation
		List<UserDoc> users = Arrays.asList(userDoc, userDoc, userDoc);
		Mockito.when(repoMock.countAll()).thenReturn(users.size());

		// Method call
		Integer usersQty = userService.countAll();

		// Verification
		Assert.assertThat(usersQty, Matchers.is(3));
		Mockito.verify(repoMock, Mockito.times(1)).countAll();
		Mockito.verifyNoMoreInteractions(repoMock);
	}
}
