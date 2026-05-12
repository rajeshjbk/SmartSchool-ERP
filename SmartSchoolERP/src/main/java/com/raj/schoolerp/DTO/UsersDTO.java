package com.raj.schoolerp.DTO;

import com.raj.schoolerp.model.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {

	private String userName;

	private String password;

	private UserRole role;

	private String fullName;

	private String email;

	private String phone;

	private Boolean active;
}