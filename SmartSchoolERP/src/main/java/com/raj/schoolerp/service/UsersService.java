package com.raj.schoolerp.service;

import java.util.List;

import com.raj.schoolerp.DTO.AdminDTO;
import com.raj.schoolerp.DTO.UsersDTO;
import com.raj.schoolerp.entity.Users;
import com.raj.schoolerp.exception.UsersException;

public interface UsersService {

	//Adding Customer
	//insert into user ... values...(....)
	public Users addUser(UsersDTO usersDTO) throws UsersException;

	//Adding UserAdmin
	public Users addUserAdmin(AdminDTO adminDTO) throws UsersException;

	//select * from user where email=....
	public Users getUserByEmailId(String emailId) throws UsersException;

	public Users getUserDetails(Long userId) throws UsersException;

	//update user set pass=new....
	public Users changePassword(Long userId, UsersDTO usersDTO) throws UsersException;

	//select * from user
	public List<Users> getAllUserDetails() throws UsersException;

	public String deactivate(Long userId) throws UsersException;
}
