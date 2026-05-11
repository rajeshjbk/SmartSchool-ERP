package com.raj.schoolerp.DTO;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class StudentsDTO {

	@NonNull
	private String admissionNo;
	
	@NonNull
	private String fullName;
	
	@NonNull
	private LocalDate dob;
	
	@NonNull
	private String gender;
	
	@NonNull
	private String academicYear;
	
	@NonNull
	private LocalDate admissionDate;
	
	@NonNull
	private String studentStatus;
  
}
