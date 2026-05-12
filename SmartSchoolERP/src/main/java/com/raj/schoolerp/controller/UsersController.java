package com.raj.schoolerp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raj.schoolerp.DTO.UsersDTO;
import com.raj.schoolerp.exception.UsersException;
import com.raj.schoolerp.model.UserRole;
import com.raj.schoolerp.model.Users;
import com.raj.schoolerp.service.UsersService;

@RestController
@RequestMapping("schoolerp/users")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@PostMapping("/add")
	public Users addUser(@RequestBody UsersDTO usersDTO) throws UsersException {

		return usersService.addUser(usersDTO);
	}

	@PutMapping("/update/{userId}")
	public Users updateUser(@PathVariable Long userId, @RequestBody UsersDTO usersDTO) throws UsersException {

		return usersService.updateUser(userId, usersDTO);
	}

	@GetMapping("/{userId}")
	public Users getUserById(@PathVariable Long userId) throws UsersException {

		return usersService.getUserById(userId);
	}

	@GetMapping("/all")
	public List<Users> getAllUsers() throws UsersException {

		return usersService.getAllUsers();
	}

	@GetMapping("/username/{userName}")
	public Users getUserByUserName(@PathVariable String userName) throws UsersException {

		return usersService.getUserByUserName(userName);
	}

	@GetMapping("/role/{role}")
	public List<Users> getUsersByRole(@PathVariable UserRole role) throws UsersException {

		return usersService.getUsersByRole(role);
	}

	@PatchMapping("/deactive/{userId}")
	public ResponseEntity<String> deactivate(@PathVariable Long userId) throws UsersException {

		String deactivate = usersService.deactivate(userId);
		return ResponseEntity.ok(deactivate);
	}

	@DeleteMapping("/delete/{userId}")
	public String deleteUserById(@PathVariable Long userId) throws UsersException {

		return usersService.deleteUserById(userId);
	}

	@DeleteMapping("/bulk-delete")
	public String deleteUsers(@RequestBody List<Long> userIds) throws UsersException {

		return usersService.deleteUsers(userIds);
	}
}
