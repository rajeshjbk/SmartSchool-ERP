package com.raj.schoolerp.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class ExamsDTO {

	@NonNull
	private String examName;
	
	@NonNull
	private String academicYear;
	
	@NonNull
	private Date startDate;
	
	@NonNull
	private Date endDate;
	
	private Date resultDate;
	
	@NonNull
	private String examStatus;
	
}
