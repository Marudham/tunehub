package com.tunehub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tunehub.entities.Users;
import com.tunehub.services.SongService;
import com.tunehub.services.UsersService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class UsersController {

	@Autowired
	UsersService userService;
	
	@Autowired
	SongService songService;
	
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
			HttpSession session, Model model) {
		
		if(userService.validateUser(email, password)) {
			String role = userService.getUserRole(email);
			//
			session.setAttribute("email", email);
			if(role.equals("Admin")) {
				return "adminHome";
			}else {
				model.addAttribute("isPremium", userService.getUser(email).isPremium());
				model.addAttribute("songs", songService.fetchAllSongs());
				return "customerHome";
			}
		}else {
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
}
