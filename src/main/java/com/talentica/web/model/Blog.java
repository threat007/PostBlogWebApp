package com.talentica.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class Blog implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@OneToMany
	@Column(name="creator_id")
	private User creatorId;

	private String title;
	@OneToMany
	@Column(name="reviewer_id")
	private User reviewerId;
	@Column(name="blog_content")
	private String blogContent;
	private String status;
	@Column(name="created_at")
	@OrderBy
	private Date createdAt = new Date();
	@Column(name="modified_at")
	private Date modifiedAt;

}