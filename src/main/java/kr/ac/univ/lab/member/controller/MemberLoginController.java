package kr.ac.univ.lab.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.univ.lab.member.dto.UserDto;
import kr.ac.univ.lab.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberLoginController {
	private final MemberService memberService;
	
	public MemberLoginController(MemberService memberService) {
		this.memberService = memberService;
	}
		
	// Login Page
	@GetMapping("/index")
	public String index(@AuthenticationPrincipal UserDto userDto, Model model) {
		System.out.println(userDto);
		model.addAttribute("userDto", userDto);
		
		return "/login/index";
	}

	// Login Page
	@GetMapping("/login")
	public String login() {
		
		return "/login/login";
	}

	// Login Fail
	@PostMapping("/login/fail")
	public String fail(HttpServletRequest request, String errormsg) {
		
		return "/login/login";
	}

	// Login Success
	@GetMapping("/login/success")
	public String result() {

		return "/home/loginResult";
	}

	// Logout
	@GetMapping("/logout/result")
	public String logout() {

		return "/login/logout";
	}

	// Permission Denied
	@GetMapping("/permission-denied")
	public String permissionDenied() {

		return "/login/denied";
	}
}