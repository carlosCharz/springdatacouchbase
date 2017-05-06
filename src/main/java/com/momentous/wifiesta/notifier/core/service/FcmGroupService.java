package com.momentous.wifiesta.notifier.core.service;

import com.momentous.wifiesta.notifier.core.dao.doc.FcmGroupDoc;

/**
 * Interface for the FCM Group Service Implementation
 *
 * @author Charz++
 */

public interface FcmGroupService {

	FcmGroupDoc findByUserId(Long userId);
	
	FcmGroupDoc findOne(String id);

}
