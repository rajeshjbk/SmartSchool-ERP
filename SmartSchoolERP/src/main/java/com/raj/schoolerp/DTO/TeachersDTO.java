package com.raj.schoolerp.DTO;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class TeachersDTO {

	@NonNull
	private String employeeId;

	@NonNull
	private String department;

	@NonNull
	private String designation;

	@NonNull
	private LocalDate joiningDate;

	@NonNull
	private Double salary;

	@NonNull
	private String qualification;

	@NonNull
	private String teacherStatus;

}
