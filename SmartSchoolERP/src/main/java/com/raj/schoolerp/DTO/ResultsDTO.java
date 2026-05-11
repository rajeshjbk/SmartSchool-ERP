package com.raj.schoolerp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class ResultsDTO {

	@NonNull
	private Double marksObtained;
	
	@NonNull
	private String grade;
	
	@NonNull
	private Double gradePoint;
	
	@NonNull
	private Integer rankInClass;
	
	@NonNull
	private Boolean isAbsent;
	
	private String remarks;
}
