package com.talentica.web.controller;

import com.talentica.web.model.Blog;
import com.talentica.web.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/blog/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
public class BlogController {

	@Autowired
	private BlogService blogService;
	@PostMapping
	public ResponseEntity<?> create(@PathVariable long userId, @Valid @RequestBody Blog blog){

		blog = blogService.createBlog(blog);
		return findAll();
	}

	@PutMapping
	public ResponseEntity<?> modify(@PathVariable long userId, @Valid @RequestBody Blog blog){
		blogService.modify(blog, userId);
		return findAll();
	}

	@GetMapping
	public  ResponseEntity<?> findAll(){
		List<Blog> blogs = blogService.findAll();
		return new ResponseEntity<>(blogs, HttpStatus.OK);

	}
}
