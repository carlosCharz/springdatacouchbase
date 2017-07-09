package com.wedevol.springdatacouchbase.core.service.impl;

import java.util.Arrays;

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
		final UserDoc user = userService.findById(USER_ONE_ID);

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
		final UserDoc user = userService.findById(USER_ONE_ID);

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
		final UserDoc user = userService.create(userDoc);

		// Verification
		Assert.assertNull(user);
		Mockito.verify(repoMock, Mockito.times(1)).findByEmail(Mockito.anyString());
		Mockito.verifyNoMoreInteractions(repoMock);
	}

}
