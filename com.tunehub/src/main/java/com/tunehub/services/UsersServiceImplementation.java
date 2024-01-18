package com.tunehub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.entities.Users;
import com.tunehub.repositories.UsersRepository;

@Service
public class UsersServiceImplementation implements UsersService{

	@Autowired
	UsersRepository usersRepo;
	
	@Override
	public String addUsers(Users user) {
		usersRepo.save(user);
		return "User added succesfully";
	}

	@Override
	public boolean emailExist(String email) {
		if(usersRepo.findByEmail(email) == null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean validateUser(String email, String password) {
		Users user = usersRepo.findByEmail(email);
		if(user == null) {
			return false;
		}
		String db_password = user.getPassword();
		if(password.equals(db_password)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public String getUserRole(String email) {
		Users user = usersRepo.findByEmail(email);
		return user.getRole();
	}

	@Override
	public Users getUser(String email) {
		return usersRepo.findByEmail(email);
	}

	@Override
	public void updateUser(Users user) {
		usersRepo.save(user);
	}

}
