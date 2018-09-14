package com.talentica.web.controller;

import com.talentica.web.service.BlogService;
import com.talentica.web.service.LoginService;
import com.talentica.web.model.User;
import com.talentica.web.model.Blog;
import com.talentica.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

@Controller
public class LoginController {

	@Autowired LoginService loginService;
	@Autowired private UserService userService;
	@Autowired private BlogService blogService;

	@GetMapping("/home")
	public String showLoginPage(ModelMap model){
		model.addAttribute("user", new User());
		model.addAttribute("blog", new Blog());
		return "home";
	}

	@GetMapping("/welcomeUser/{userId}")
	public String showUserPage(Model mv, @PathVariable long userId){
		userPageDetails(mv, userId);
		return "userPage";
	}

	@GetMapping("/welcomeAdmin/{userId}")
	public String showAdminPage(Model mv, @PathVariable long userId){
		userPageDetails(mv, userId);
		List<User> userList = userService.findAll();
		mv.addAttribute("userList", userList);
		return "adminPage";
	}

	private void userPageDetails(Model mv, @PathVariable long userId) {
		User user = userService.findOne(userId);
		List<Blog> blogList = blogService.getAllApprovedBlogs("Approved");
		List<Blog> myBlogs = blogService.findBlogByCreatorId(userId);
		mv.addAttribute("user",user);
		mv.addAttribute("BlogList",blogList);
		mv.addAttribute("myBlogs",myBlogs);
	}
}
