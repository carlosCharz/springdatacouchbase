package com.momentous.wifiesta.notifier.core.dao.doc;

import java.io.Serializable;

import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;

/**
 * Represents a FCM Group doc from Couchbase
 *
 * @author Charz++
 */

@Document
public class FcmGroupDoc implements Serializable {

	private static final long serialVersionUID = 3072475211055736282L;

	@Field
	private Long userId;
	@Field
	private String hash;

	public FcmGroupDoc() {
	}

	public FcmGroupDoc(Long userId, String hash) {
		this.userId = userId;
		this.hash = hash;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	@Override
	public String toString() {
		return String.format("FcmGroup [userId=%s, hash=%s]", userId, hash);
	}

	@Override
	public int hashCode() {
		int hashed = 1;
		if (userId != null) {
			hashed = hashed * 31 + userId.hashCode();
		}
		if (hash != null) {
			hashed = hashed * 31 + hash.hashCode();
		}
		return hashed;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		if (obj == this)
			return true;
		FcmGroupDoc other = (FcmGroupDoc) obj;
		return this.hashCode() == other.hashCode();
	}

}
