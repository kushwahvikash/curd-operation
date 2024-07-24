package com.vikas.co.services;

import java.util.List;
import java.util.Optional;

import com.vikas.co.entity.User;

public interface UserServices {

	public User saveUser(User user);
	public List<User> getAllUsers();
	public Optional<User> getUserById(Integer id);
	public List<User> getUsersByName(String name);
	public List<User> getUsersByEmail(String email);
	public User updateUser(Integer id, User userDetails);
	public void deleteUserById(Integer id);
	public void deleteAllUsers();
	public boolean existsByEmail(String email);
}
