package com.raj.schoolerp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class SubjectsDTO {

	@NonNull
	private String subjectName;
	
	@NonNull
	private String SubjectCode;
	
	@NonNull
	private String  subjectType ;
	
	@NonNull
	private Integer creditHrs;
	
	@NonNull
	private Boolean isElective;
		
}
