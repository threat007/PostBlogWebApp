package com.talentica.web.controller;

import com.talentica.web.model.Blog;
import com.talentica.web.model.User;
import com.talentica.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public String create(@Valid @RequestBody User user){
		userService.createUser(user);
		return "User Created";
	}

	@GetMapping("/userId/{userId}")
	public ResponseEntity<?> findAll(@PathVariable long userId){
		List<User> users = userService.findAll(userId);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
}
