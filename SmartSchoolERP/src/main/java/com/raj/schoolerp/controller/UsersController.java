package com.raj.schoolerp.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raj.schoolerp.DTO.AdminDTO;
import com.raj.schoolerp.DTO.UsersDTO;
import com.raj.schoolerp.exception.UsersException;
import com.raj.schoolerp.model.Users;
import com.raj.schoolerp.service.UsersService;

@RestController
@RequestMapping("schoolerp/users")
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	/*@Autowired
	private PasswordEncoder passwordEncoder;
	*/
	@PostMapping("/add-user")
	public ResponseEntity<Users> addUser(@RequestBody UsersDTO dto)throws UsersException{
		
		/*dto.setPassword(passwordEncoder.encode(dto.getPassword()));*/
		Users addUser = usersService.addUser(dto);
		
		return ResponseEntity.ok(addUser);
	}
	
	@PostMapping("/add-admin")
	public ResponseEntity<Users> addUserAdmin(@RequestBody AdminDTO dto)throws UsersException{
		
		/*dto.setPassword(passwordEncoder.encode(dto.getPassword()));*/
		Users userAdmin = usersService.addUserAdmin(dto);
		return ResponseEntity.ok(userAdmin);
	}
	
	@GetMapping("/{emailId}")
	public ResponseEntity<Users> getUserByEmailId(@PathVariable String emailId) throws UsersException{
		
		Users userByEmailId = usersService.getUserByEmailId(emailId);
		return ResponseEntity.ok(userByEmailId);
	}
	
	@GetMapping("/details/{userId}")
	public ResponseEntity<Users> getUserDetails(@PathVariable Long userId) throws UsersException{
		
		Users userDetails = usersService.getUserDetails(userId);
		return ResponseEntity.ok(userDetails);
	}
	
	@PutMapping("/update-user/{userId}")
	public ResponseEntity<Users> changePassword(@PathVariable Long userId, @RequestBody UsersDTO usersDTO) throws UsersException{
		
		Users changePassword = usersService.changePassword(userId, usersDTO);
		return ResponseEntity.ok(changePassword);
	}
	
	@GetMapping("/all-users")
	public ResponseEntity< List<Users>> getAllUserDetails() throws UsersException {
		
		List<Users> allUserDetails = usersService.getAllUserDetails();
		return ResponseEntity.ok(allUserDetails);
	}
	
	@PatchMapping("/deactive/{userId}")
	public ResponseEntity<String> deactivate(@PathVariable Long userId) throws UsersException {
		
		String deactivate = usersService.deactivate(userId);
		return ResponseEntity.ok(deactivate);
	}
}
