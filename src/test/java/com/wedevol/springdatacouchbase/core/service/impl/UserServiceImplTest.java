package com.wedevol.springdatacouchbase.core.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.wedevol.springdatacouchbase.core.dao.UserRepository;
import com.wedevol.springdatacouchbase.core.dao.doc.UserDoc;
import com.wedevol.springdatacouchbase.core.exception.ApiException;

/**
 * Test the UserServiceImpl
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
		userDoc.setAge(25);
		userDoc.setEmail("carlos1@yopmail.com");
	}

	@After
	public void tearDown() {
	}
	
	@Test
	public void findOneAndUserExists() {
		// Data preparation
		when(repoMock.findOne(USER_ONE_KEY)).thenReturn(userDoc);

		// Method call
		final UserDoc user = userService.findById(USER_ONE_ID);

		// Verification
		assertNotNull(user);
		verify(repoMock, times(1)).findOne(USER_ONE_KEY);
		verifyNoMoreInteractions(repoMock);
	}
	
	@Test(expected = ApiException.class)
	public void findOneAndUserIsNull() {
		// Data preparation
		when(repoMock.findOne(USER_ONE_KEY)).thenReturn(null);

		// Method call
		final UserDoc user = userService.findById(USER_ONE_ID);

		// Verification
		assertNull(user);
		verify(repoMock, times(1)).findOne(USER_ONE_KEY);
		verifyNoMoreInteractions(repoMock);
	}

}
