package com.talentica.web.service;

import com.talentica.web.model.User;
import com.talentica.web.repository.BlogRepository;
import com.talentica.web.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;

	public Blog createBlog(Blog blog) {
		return blogRepository.save(blog);
	}

	public List<Blog> getAllApprovedBlogs(String status) {
		return blogRepository.findAllByStatus(status);
	}

	public List<Blog> findAllUserBlogs(User user) {
		return blogRepository.findAll();
	}

	public void modify(Blog blog, long userId) {
		Blog blogInDb = blogRepository.findOne(blog.getId());
		if(blogInDb.getCreatorId() == userId) {
			blogRepository.save(blog);
		}
	}

	public void deleteBlog(long id) {
		blogRepository.delete(id);
	}

	public List<Blog> findBlogByCreatorId(long userId) {
		return blogRepository.findAllByCreatorId(userId);
	}
}
