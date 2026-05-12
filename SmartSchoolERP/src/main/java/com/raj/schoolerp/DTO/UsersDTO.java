package com.raj.schoolerp.DTO;

import com.raj.schoolerp.model.UserRole;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
	
	@NotNull(message = "Username is Mandatory")
	private String userName;

	@NotNull(message = "User Password is Mandatory")
	private String password;

	@NotNull(message = "User Role is Mandatory")
	@Enumerated(EnumType.STRING)
	private UserRole role; 

	@NotNull(message = "User Fullname is Mandatory")
	private String fullName;

	@NotNull(message = "User Email is Mandatory")
	private String email;

	@NotNull(message = "User Mobile is Mandatory")
	private String phone;  

}
