package com.raj.schoolerp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raj.schoolerp.DTO.UserSigninDetail;
import com.raj.schoolerp.exception.UsersException;
import com.raj.schoolerp.model.Users;
import com.raj.schoolerp.service.UsersService;

@RestController
@RequestMapping("/schoolerp")
@CrossOrigin(origins = "http://localhost:5173")
public class LoginController {

	@Autowired
	private UsersService usersService;

	@PostMapping("/signIn")
	public ResponseEntity<UserSigninDetail> getLoggedInUsersDetailsHandler(Authentication authObj)
			throws UsersException {

		if (authObj == null) {

			throw new UsersException("Invalid Username or Password");
		}

		try {

			Users userObj = usersService.getUserByUserName(authObj.getName());

			UserSigninDetail signinSuccessData = new UserSigninDetail();

			signinSuccessData.setId(userObj.getUserId());

			signinSuccessData.setFullName(userObj.getFullName());

			signinSuccessData.setSignInStatus("SUCCESS");

			signinSuccessData.setUserRole(userObj.getRole().toString());

			return new ResponseEntity<>(signinSuccessData, HttpStatus.OK);

		} catch (Exception e) {

			throw new UsersException("Invalid Username or Password");
		}
	}
}