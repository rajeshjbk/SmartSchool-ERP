package com.raj.schoolerp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class FeeStructuresDTO {
	
	@NonNull
	private String feeType;
	
	@NonNull
	private Double amount;
	
	@NonNull
	private String frequency;
	
	@NonNull
	private Integer dueDay;
	
	@NonNull
	private String academicYear;
	
	private Boolean isMandatory;
	
	private Double lateFine;
		
}
