package com.wedevol.springdatacouchbase.core.dao.doc;

import java.io.Serializable;

import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;

/**
 * Represents a user basic doc
 *
 * @author Charz++
 */

@Document
public class UserBasicDoc implements Serializable {

	private static final long serialVersionUID = 3072475211055736282L;

	@Field("id")
	private Long id;
	@Field("name")
	private String name;

	public UserBasicDoc() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return String.format("User Basic [id=%s, name=%s]", id, name);
	}

	@Override
	public int hashCode() {
		int hashed = 1;
		if (id != null) {
			hashed = hashed * 31 + id.hashCode();
		}
		if (name != null) {
			hashed = hashed * 31 + name.hashCode();
		}
		return hashed;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		if (obj == this)
			return true;
		UserBasicDoc other = (UserBasicDoc) obj;
		return this.hashCode() == other.hashCode();
	}

}
