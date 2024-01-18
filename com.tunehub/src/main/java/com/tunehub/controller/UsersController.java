package com.tunehub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tunehub.entities.Users;
import com.tunehub.services.UsersService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class UsersController {

	@Autowired
	UsersService userService;
	
	@PostMapping("/register")
	public String addUsers(@ModelAttribute Users user) {
		
		boolean userStatus = userService.emailExist(user.getEmail());
		
		if(userStatus == false) {
			userService.addUsers(user);
			System.out.println("User added");
		}else {
			System.out.println("User already exist");
		}
		
		return "home";
	}
	
	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email,
			@RequestParam("password") String password,
			HttpSession session) {
		
		if(userService.validateUser(email, password)) {
			String role = userService.getUserRole(email);
			//
			session.setAttribute("email", email);
			if(role.equals("Admin")) {
				return "adminHome";
			}else {
				return "customerHome";
			}
		}else {
			return "login";
		}
	}

/*	@GetMapping("/pay")
	public String pay(@RequestParam String email) {
		boolean paymentStatus = false;//payment api
		if(paymentStatus) {
			Users user = userService.getUser(email);
			user.setPremium(true);
			userService.updateUser(user);
		}
		return "login";
	}
*/
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
}
