package com.talentica.web.controller;

import com.talentica.web.model.Blog;
import com.talentica.web.model.User;
import com.talentica.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Controller
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/create", produces="application/json;charset=UTF-8")
	public String create(HttpServletRequest user, Model model){
		User newUser = new User();
		newUser.setFullName(user.getParameter("fullName"));
		newUser.setEmailId(user.getParameter("emailId"));
		newUser.setUsername(user.getParameter("username"));
		newUser.setPassword(user.getParameter("password"));
		userService.createUser(newUser); // return user obj and set in mv below
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+user.getParameter("fullName"));
		// Mock data - start
		User creator = new User(100, "FirstUser", "abc@123", "Amit Ranjan", "amitranjan@gmail.com", User.UserRole.USER, Calendar.getInstance(), Calendar.getInstance() );
		User reviewer = new User(200, "AdminUser", "abc@123", "Ashok Ranjan", "ashokranjan@gmail.com", User.UserRole.ADMIN, Calendar.getInstance(), Calendar.getInstance() );
		List<Blog> bList = new ArrayList<Blog>();
		/*Blog blogCreated = new Blog(123,creator,"My First Blog",reviewer, "My first blog contents","active", new Date(),new Date());
		Blog blogCreated1 = new Blog(123,creator,"My Second Blog",reviewer, "My second blog contents","active", new Date(),new Date());
		Blog blogCreated2 = new Blog(123,creator,"My Third Blog",reviewer, "My second blog contents","active", new Date(),new Date());
		bList.add(blogCreated);
		bList.add(blogCreated1);
		bList.add(blogCreated2);*/
		//Mock data - end
		model.addAttribute("user",creator);
		model.addAttribute("blogs",bList); // replace 2nd attribute with - blogs ByUser
		return "userPage";
	}

	@GetMapping
	public ResponseEntity<?> findAll(@PathVariable long userId){
		List<User> users = userService.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/userId/{userId}")
	public ResponseEntity<?> findOne(@PathVariable long userId){
		User user = userService.findOne(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
