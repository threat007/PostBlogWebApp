package com.talentica.web.controller;

import com.talentica.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@Autowired LoginService loginService;

	@GetMapping("/home")
	public String showLoginPage(ModelMap model){
		return "home";
	}

	@PostMapping("/home/login")
	public String showWelcomePage(ModelMap model, @RequestParam String userName, @RequestParam String password){

		System.out.println("Name >>>>>> "+userName);
		boolean isValidUser = true;//loginService.validateUser(name);

		if (!isValidUser) {
			model.put("errorMessage", "Invalid Credentials");
			return "Invalid Credentials";
		}

		model.put("name", userName);
		model.put("password", password);

		return "userPage";
	}
}
