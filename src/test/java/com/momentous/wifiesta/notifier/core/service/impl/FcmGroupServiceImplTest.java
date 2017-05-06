package com.momentous.wifiesta.notifier.core.service.impl;

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

import com.momentous.wifiesta.notifier.core.dao.FcmGroupRepository;
import com.momentous.wifiesta.notifier.core.dao.doc.FcmGroupDoc;

/**
 * Test the FcmGroupServiceImpl
 *
 * @author Charz++
 */

@RunWith(MockitoJUnitRunner.class)
public class FcmGroupServiceImplTest {
	
	private static final String userOneGroup = "user::1::fcmgroup";

	@Mock
	private FcmGroupRepository repoMock;

	@InjectMocks
	private FcmGroupServiceImpl fcmGroupService;

	private FcmGroupDoc fcmGroupDoc;

	private Long userId;

	@Before
	public void init() {
		fcmGroupDoc = new FcmGroupDoc();
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
		final FcmGroupDoc fcmGroup = fcmGroupService.findOne(userOneGroup);

		// Verification
		assertNotNull(fcmGroup);
		verify(repoMock, times(1)).findOne(userOneGroup);
		verifyNoMoreInteractions(repoMock);
	}

	@Test
	public void findByUserIdAndFcmGroupExists() throws Exception {
		// Data preparation
		List<FcmGroupDoc> fcmGroupList = new ArrayList<FcmGroupDoc>();
		fcmGroupList.add(fcmGroupDoc);
		when(repoMock.findByUserId(1L)).thenReturn(fcmGroupList);

		// Method call
		final FcmGroupDoc fcmGroup = fcmGroupService.findByUserId(userId);

		// Verification
		assertNotNull(fcmGroup);
		verify(repoMock, times(1)).findByUserId(1L);
		verifyNoMoreInteractions(repoMock);
	}

	@Test
	public void findByUserIdAndFcmGroupDoesNotExist() throws Exception {
		// Data preparation
		List<FcmGroupDoc> fcmGroupList = new ArrayList<FcmGroupDoc>();
		when(repoMock.findByUserId(1L)).thenReturn(fcmGroupList);

		// Method call
		final FcmGroupDoc token = fcmGroupService.findByUserId(userId);

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
		final FcmGroupDoc fcmGroup = fcmGroupService.findByUserId(userId);

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
		final FcmGroupDoc fcmGroup = fcmGroupService.findOne(userOneGroup);

		// Verification
		assertNull(fcmGroup);
		verify(repoMock, times(1)).findOne(userOneGroup);
		verifyNoMoreInteractions(repoMock);
	}

	@Test
	public void finByUserIdAndThereAreManyFcmGroups() throws Exception {
		// Data preparation
		List<FcmGroupDoc> fcmGroupList = new ArrayList<FcmGroupDoc>();
		fcmGroupList.add(fcmGroupDoc);
		fcmGroupList.add(fcmGroupDoc);
		when(repoMock.findByUserId(1L)).thenReturn(fcmGroupList);

		// Method call
		final FcmGroupDoc token = fcmGroupService.findByUserId(userId);

		// Verification
		assertNull(token);
		verify(repoMock, times(1)).findByUserId(1L);
		verifyNoMoreInteractions(repoMock);
	}

}
