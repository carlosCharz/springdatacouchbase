package com.momentous.wifiesta.notifier.core.dao;

import java.util.List;

import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.momentous.wifiesta.notifier.core.dao.doc.FcmGroupDoc;

/**
 * Standard CRUD repository for FCM group doc + query methods
 *
 * @author Charz++
 */

public interface FcmGroupRepository extends CrudRepository<FcmGroupDoc, String> {

	// The docs are indexed by type
	@Query("#{#n1ql.selectEntity} WHERE type='fcmg' and userId = $1")
	List<FcmGroupDoc> findByUserId(Long userId);
	
}
