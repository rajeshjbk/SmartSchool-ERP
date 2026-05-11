package com.raj.schoolerp.DTO;

import java.time.LocalTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class TimetableDTO {

	
	@NonNull
	private String dayOfWeek;
	
	@NonNull
	private Integer periodOfTime;
	
	@NonNull
	private LocalTime startTime;
	
	@NonNull
	private LocalTime endTime;
	
	@NonNull
	private String roomNo;
	
	
}
