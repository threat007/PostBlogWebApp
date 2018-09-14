package com.talentica.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Entity
public class Blog implements Serializable {

	public Blog(){

	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@JoinColumn(name ="id")
	@Column(name="creator_id")
	private long creatorId;

	private String title;

	@JoinColumn(name ="id")
	@Column(name="reviewer_id")
	private long reviewerId;

	@Column(name="blog_content")
	private String blogContent;

	private String status;

	@Column(name="created_at")
	@OrderBy
	private Date createdAt = new Date();
	@Column(name="modified_at")
	private Date modifiedAt;

	public Blog(long creatorId, String title, long reviewerId, String blogContent, String status) {
		this.creatorId = creatorId;
		this.title = title;
		this.reviewerId = reviewerId;
		this.blogContent = blogContent;
		this.status = status;
	}
}