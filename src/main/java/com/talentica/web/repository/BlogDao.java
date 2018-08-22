package com.talentica.web.repository;

import com.talentica.web.model.Blog;
import com.talentica.web.util.AppUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Repository
public class BlogDao {
	public Blog createBlog(Blog blog) {
		EntityManager entityManager = AppUtil.beginTransaction();
		entityManager.persist(blog);
		entityManager.flush();
		AppUtil.commitTransaction();
		return blog;
	}

	public List<Blog> findAll() {
		EntityManager entityManager = AppUtil.beginTransaction();
		List<Blog> blogs = entityManager.createNamedQuery("select * From Blog", Blog.class).getResultList();
		AppUtil.commitTransaction();
		return blogs;
	}

	public void modify(Blog blog, long userId) {
		EntityManager entityManager = AppUtil.beginTransaction();
		Blog blogInDb = entityManager.find(Blog.class,blog.getId());
		if(blogInDb.getCreatorId().getId() == userId) {
			blogInDb.setBlogContent(blog.getBlogContent());
			blogInDb.setModifiedAt(new Date());
			entityManager.flush();
		}
		AppUtil.commitTransaction();
	}
}
