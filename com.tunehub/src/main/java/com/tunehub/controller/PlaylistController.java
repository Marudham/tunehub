package com.tunehub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tunehub.entities.Playlist;
import com.tunehub.entities.Song;
import com.tunehub.services.PlaylistService;
import com.tunehub.services.SongService;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PlaylistController {
	
	@Autowired
	SongService songService;

	@Autowired
	PlaylistService playlistService;
	
	@GetMapping("/createPlaylist")
	public String createPlaylist(Model model) {
		model.addAttribute("songs", songService.fetchAllSongs());
		return "createPlaylist";
	}
	
	@PostMapping("/addPlaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		playlistService.addPlaylist(playlist);
//		System.out.println(playlist);
		for(Song song : playlist.getSongs()) {
//			System.out.println(song);
			song.getPlaylists().add(playlist);
			songService.updateSong(song);
		}
		return "adminHome";
	}
	
	@GetMapping("/viewPlaylists")
	public String viewPlaylists(Model model) {
		model.addAttribute("playlists",playlistService.fetchAllPlaylist());
		return "viewPlaylists";
	}
	
}
