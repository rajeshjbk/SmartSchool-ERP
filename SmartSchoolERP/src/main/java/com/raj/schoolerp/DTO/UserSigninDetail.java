package com.raj.schoolerp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSigninDetail {

	private Long id;
	private String fullName;
	private String signInStatus;
	private String userRole;
	
}
