package com.tunehub.services;

import java.util.List;

import com.tunehub.entities.Song;

public interface SongService {

	void addSong(Song song);

	List<Song> fetchAllSongs();
	
	boolean songExist(String name);

	void updateSong(Song song);
}
