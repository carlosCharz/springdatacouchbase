package com.wedevol.springdatacouchbase.core.dao.doc;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

/**
 * Represents a user doc from Couchbase
 *
 * @author Charz++
 */

@Document
public class UserDoc implements Serializable {

	private static final long serialVersionUID = 3072475211055736282L;
	protected static final String userKeyPrefix = "user::";

	@Id
	@Field
	private String id;
	@Field
	@NotNull
	private String name;
	@Field
	private List<String> nicknames;
	@Field
	@NotNull
	private Integer age;
	@Field
	@NotNull
	private String email;

	public UserDoc() {
	}

	public UserDoc(String id, String name, List<String> nicknames, Integer age, String email) {
		super();
		this.id = id;
		this.name = name;
		this.nicknames = nicknames;
		this.age = age;
		this.email = email;
	}
	
	public static String getKeyFor(Long id) {
		return userKeyPrefix + id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getNicknames() {
		return nicknames;
	}

	public void setNicknames(List<String> nicknames) {
		this.nicknames = nicknames;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		if (nicknames != null) {
			hashed = hashed * 31 + nicknames.hashCode();
		}
		if (age != null) {
			hashed = hashed * 31 + age.hashCode();
		}
		if (email != null) {
			hashed = hashed * 31 + email.hashCode();
		}
		return hashed;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		if (obj == this)
			return true;
		UserDoc other = (UserDoc) obj;
		return this.hashCode() == other.hashCode();
	}

}
