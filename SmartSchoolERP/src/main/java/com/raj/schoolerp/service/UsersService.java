package com.raj.schoolerp.service;

import java.util.List;

import com.raj.schoolerp.DTO.UsersDTO;
import com.raj.schoolerp.exception.UsersException;
import com.raj.schoolerp.model.UserRole;
import com.raj.schoolerp.model.Users;

public interface UsersService {

	// Add User
	Users addUser(UsersDTO usersDTO) throws UsersException;

	// Update User
	Users updateUser(Long userId, UsersDTO usersDTO) throws UsersException;

	// Get User By Id
	Users getUserById(Long userId) throws UsersException;

	// Get All Users
	List<Users> getAllUsers() throws UsersException;

	// Get User By Username
	Users getUserByUserName(String userName) throws UsersException;

	// Get Users By Role
	List<Users> getUsersByRole(UserRole role) throws UsersException;

	// Delete User By Id
	String deleteUserById(Long userId) throws UsersException;

	// Bulk Delete Users
	String deleteUsers(List<Long> userIds) throws UsersException;

	public String deactivate(Long userId) throws UsersException;
}
