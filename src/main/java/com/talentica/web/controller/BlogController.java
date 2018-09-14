package com.talentica.web.controller;

import com.talentica.web.model.Blog;
import com.talentica.web.model.User;
import com.talentica.web.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/{userId}/blog", produces = MediaType.APPLICATION_JSON_VALUE)
public class BlogController {

	@Autowired
	private BlogService blogService;
	@PostMapping
	public String create(@PathVariable long userId, HttpServletRequest request, Model model){
		String title = request.getParameter("title");
		String body = request.getParameter("blogContent");
		Blog blog = new Blog(userId, title, 1, body, "Pending");
		blogService.createBlog(blog);
		List<Blog> blogList = findAprovedBlogs();
		List<Blog> myBlogs = blogService.findBlogByCreatorId(userId);
		model.addAttribute("approvedBlogs", blogList);
		model.addAttribute("myBlogs", myBlogs);
		return "userPage";
	}

	@PutMapping("/modify")
	public List<Blog> modify(@PathVariable long userId, @Valid @RequestBody Blog blog){
		blogService.modify(blog, userId);
		return findAprovedBlogs();
	}

	@GetMapping
	public  List<Blog> findAprovedBlogs(){
		return blogService.getAllApprovedBlogs("Approved");
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@Valid @RequestBody Blog blog){
		blogService.deleteBlog(blog.getId());
		return null;
	}
}
