package com.tunehub.services;

import java.util.List;

import com.tunehub.entities.Playlist;

public interface PlaylistService {

	String addPlaylist(Playlist playlist);

	List<Playlist> fetchAllPlaylist();
}
