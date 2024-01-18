package com.tunehub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.entities.Playlist;
import com.tunehub.repositories.PlaylistRepository;

@Service
public class PlaylistServiceImplementation implements PlaylistService{

	@Autowired
	PlaylistRepository playlistRepo;
	
	@Override
	public String addPlaylist(Playlist playlist) {
		playlistRepo.save(playlist);
		return "Playlist added";
	}

	@Override
	public List<Playlist> fetchAllPlaylist() {
		return playlistRepo.findAll();
	}

}
