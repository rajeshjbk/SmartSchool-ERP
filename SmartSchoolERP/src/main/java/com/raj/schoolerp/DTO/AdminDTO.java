package com.raj.schoolerp.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {

	@NotNull(message = "Username is Mandatory")
	private String userName;

	@NotNull(message = "User Password is Mandatory")
	private String password;

	@NotNull(message = "User Fullname is Mandatory")
	private String fullName;

	@NotNull(message = "User Email is Mandatory")
	private String email;

	@NotNull(message = "User Mobile is Mandatory")
	private String phone;  
}
