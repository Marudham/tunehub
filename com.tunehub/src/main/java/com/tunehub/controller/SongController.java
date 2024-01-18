package com.tunehub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tunehub.entities.Song;
import com.tunehub.services.SongService;

@Controller
public class SongController {

	@Autowired
	SongService service;
	
	@PostMapping("/addSong")
	public String addSong(@ModelAttribute Song song) {
		boolean songStatus = service.songExist(song.getName());
		if(!songStatus) {
			service.addSong(song);
			System.out.println("Song added");
		}else{
			System.out.println("Song already exist");
		}
		return "adminHome";
	}
	
	@GetMapping("/viewSongs")
	public String viewSongs(Model model) {
		model.addAttribute("songs", service.fetchAllSongs());
		return "viewSongs";
	}
	
	@GetMapping("/playSongs")
	public String playSongs(Model model) {
		boolean premiumUser = false;
		if(premiumUser) {
//			model.addAttribute("songs", service.fetchAllSongs());
//			return "viewSongs";
			return viewSongs(model);
		}else {
			return "makePayment";
		}
	}
}
