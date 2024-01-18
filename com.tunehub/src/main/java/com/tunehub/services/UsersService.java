package com.tunehub.services;

import com.tunehub.entities.Users;

public interface UsersService {

	String addUsers(Users user);
	
	boolean emailExist(String email);
	
	boolean validateUser(String email, String password);
	
	String getUserRole(String email);

	Users getUser(String email);

	void updateUser(Users user);
}
