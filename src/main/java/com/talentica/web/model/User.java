package com.talentica.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.Calendar;

@Getter
@ToString
@AllArgsConstructor
@Entity
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true)
	private String username;

	@JsonIgnore
	@Getter(AccessLevel.NONE)
	private String password;

	private String fullName;

	@Column(unique = true)
	private String emailId;

	private UserRole role;

	private Calendar createdAt;

	private Calendar modifiedAt;

	public enum UserRole {
		SUPER_ADMIN, ADMIN, BLOGGER
	}
}
