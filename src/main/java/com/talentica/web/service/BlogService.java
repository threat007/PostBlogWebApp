package com.talentica.web.service;

import com.talentica.web.repository.BlogDao;
import com.talentica.web.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;

	public Blog createBlog(Blog blog) {
		return blogDao.createBlog(blog);
	}

	public List<Blog> findAll() {
		return blogDao.findAll();
	}

	public void modify(Blog blog, long userId) {
		blogDao.modify(blog, userId);
	}
}
