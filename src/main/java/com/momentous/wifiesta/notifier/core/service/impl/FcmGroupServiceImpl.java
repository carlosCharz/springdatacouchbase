package com.momentous.wifiesta.notifier.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momentous.wifiesta.notifier.core.dao.FcmGroupRepository;
import com.momentous.wifiesta.notifier.core.dao.doc.FcmGroupDoc;
import com.momentous.wifiesta.notifier.core.exception.ErrorType;
import com.momentous.wifiesta.notifier.core.exception.ServerException;
import com.momentous.wifiesta.notifier.core.service.FcmGroupService;

/**
 * Service that manages the valid operations over the FCM group repository
 *
 * @author Charz++
 */

@Service
public class FcmGroupServiceImpl implements FcmGroupService {

	protected static final Logger logger = LoggerFactory.getLogger(FcmGroupServiceImpl.class);

	@Autowired
	private FcmGroupRepository repo;
	
	// TODO remove logs

	@Override
	public FcmGroupDoc findByUserId(Long userId) {
		try {
			List<FcmGroupDoc> groupList = repo.findByUserId(userId);
			if (groupList == null || groupList.isEmpty()) {
				return null;
			} else if (groupList.size() > 1) {
				throw new ServerException(ErrorType.ILLEGAL_RESULT_LENGTH);
			} else {
				FcmGroupDoc group = groupList.get(0);
				logger.info("FCM Group found: {}", group);
				return group;
			}
		} catch (ServerException e) {
			logger.error("Server Error: {}", e.getMessage());
		}
		return null;
	}

	@Override
	public FcmGroupDoc findOne(String key) {
		return repo.findOne(key);
	}

}
