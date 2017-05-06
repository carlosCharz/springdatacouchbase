package com.wedevol.springdatacouchbase.core.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.wedevol.springdatacouchbase.core.dao.UserRepository;
import com.wedevol.springdatacouchbase.core.dao.doc.UserDoc;
import com.wedevol.springdatacouchbase.core.service.impl.UserServiceImpl;

/**
 * Test the UserServiceImpl
 *
 * @author Charz++
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
	
	private static final String userOneGroup = "user::1::fcmgroup";

	@Mock
	private UserRepository repoMock;

	@InjectMocks
	private UserServiceImpl fcmGroupService;

	private UserDoc fcmGroupDoc;

	private Long userId;

	@Before
	public void init() {
		fcmGroupDoc = new UserDoc();
		fcmGroupDoc.setUserId(1L);
		fcmGroupDoc.setHash("abc12456bca");

		userId = 1L;
	}

	@After
	public void tearDown() {
	}
	
	@Test
	public void findOneAndFcmGroupExists() throws Exception {
		// Data preparation
		when(repoMock.findOne(userOneGroup)).thenReturn(fcmGroupDoc);

		// Method call
		final UserDoc fcmGroup = fcmGroupService.findOne(userOneGroup);

		// Verification
		assertNotNull(fcmGroup);
		verify(repoMock, times(1)).findOne(userOneGroup);
		verifyNoMoreInteractions(repoMock);
	}

	@Test
	public void findByUserIdAndFcmGroupExists() throws Exception {
		// Data preparation
		List<UserDoc> fcmGroupList = new ArrayList<UserDoc>();
		fcmGroupList.add(fcmGroupDoc);
		when(repoMock.findByUserId(1L)).thenReturn(fcmGroupList);

		// Method call
		final UserDoc fcmGroup = fcmGroupService.findByUserId(userId);

		// Verification
		assertNotNull(fcmGroup);
		verify(repoMock, times(1)).findByUserId(1L);
		verifyNoMoreInteractions(repoMock);
	}

	@Test
	public void findByUserIdAndFcmGroupDoesNotExist() throws Exception {
		// Data preparation
		List<UserDoc> fcmGroupList = new ArrayList<UserDoc>();
		when(repoMock.findByUserId(1L)).thenReturn(fcmGroupList);

		// Method call
		final UserDoc token = fcmGroupService.findByUserId(userId);

		// Verification
		assertNull(token);
		verify(repoMock, times(1)).findByUserId(1L);
		verifyNoMoreInteractions(repoMock);
	}

	@Test
	public void findByUserIdAndFcmGroupIsNull() throws Exception {
		// Data preparation
		when(repoMock.findByUserId(1L)).thenReturn(null);

		// Method call
		final UserDoc fcmGroup = fcmGroupService.findByUserId(userId);

		// Verification
		assertNull(fcmGroup);
		verify(repoMock, times(1)).findByUserId(1L);
		verifyNoMoreInteractions(repoMock);
	}
	
	@Test
	public void findOneAndFcmGroupIsNull() throws Exception {
		// Data preparation
		when(repoMock.findOne(userOneGroup)).thenReturn(null);

		// Method call
		final UserDoc fcmGroup = fcmGroupService.findOne(userOneGroup);

		// Verification
		assertNull(fcmGroup);
		verify(repoMock, times(1)).findOne(userOneGroup);
		verifyNoMoreInteractions(repoMock);
	}

	@Test
	public void finByUserIdAndThereAreManyFcmGroups() throws Exception {
		// Data preparation
		List<UserDoc> fcmGroupList = new ArrayList<UserDoc>();
		fcmGroupList.add(fcmGroupDoc);
		fcmGroupList.add(fcmGroupDoc);
		when(repoMock.findByUserId(1L)).thenReturn(fcmGroupList);

		// Method call
		final UserDoc token = fcmGroupService.findByUserId(userId);

		// Verification
		assertNull(token);
		verify(repoMock, times(1)).findByUserId(1L);
		verifyNoMoreInteractions(repoMock);
	}

}
