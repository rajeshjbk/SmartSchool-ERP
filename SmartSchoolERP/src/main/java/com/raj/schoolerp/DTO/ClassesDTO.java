package com.raj.schoolerp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassesDTO {

	@NonNull
	private String className;
	
	@NonNull
	private String section;
	
	@NonNull
	private String academicYear;
	
	@NonNull
	private String roomNo;
	
	@NonNull
	private Integer capacity;
	
}
