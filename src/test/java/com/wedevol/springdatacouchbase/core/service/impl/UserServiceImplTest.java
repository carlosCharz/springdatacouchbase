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
	
	private static final String userOneKey = "user::1";

	@Mock
	private UserRepository repoMock;

	@InjectMocks
	private UserServiceImpl userService;

	private UserDoc userDoc;

	private Long userId;

	@Before
	public void init() {
		userDoc = new UserDoc();
		userDoc.setId(userOneKey);
		userDoc.setNicknames(Arrays.asList("charz", "carlito"));
		userDoc.setAge(25);
		userDoc.setEmail("carlos1@yopmail.com");

		userId = 1L;
	}

	@After
	public void tearDown() {
	}
	
	@Test
	public void findOneAndUserExists() {
		// Data preparation
		when(repoMock.findOne(userOneKey)).thenReturn(userDoc);

		// Method call
		final UserDoc user = userService.findById(userId);

		// Verification
		assertNotNull(user);
		verify(repoMock, times(1)).findOne(userOneKey);
		verifyNoMoreInteractions(repoMock);
	}
	
	@Test(expected = ApiException.class)
	public void findOneAndUserIsNull() {
		// Data preparation
		when(repoMock.findOne(userOneKey)).thenReturn(null);

		// Method call
		final UserDoc user = userService.findById(userId);

		// Verification
		assertNull(user);
		verify(repoMock, times(1)).findOne(userOneKey);
		verifyNoMoreInteractions(repoMock);
	}

}
