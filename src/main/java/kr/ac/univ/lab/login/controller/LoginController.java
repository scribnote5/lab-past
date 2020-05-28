package kr.ac.univ.lab.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class LoginController {

	// Login Page
	@GetMapping("/")
	public String index() {
		
		return "/login/index";
	}
	
	// Login Page
	@GetMapping("/login")
	public String login() {
		
		return "/login/login";
	}
	
	// Login Result
	@GetMapping("/login/result")
	public String result() {
		System.out.println("/login/result!!!!");
		return "/login/loginResult";
	}
	
	// Logout
	@GetMapping("/logout/result")
	public String logout() {
		
		return "/login/logut";
	}
	
	// Permission Denied
	@GetMapping("/permission-denied")
	public String permissionDenied() {
		
		return "/login/denied";
	}
}