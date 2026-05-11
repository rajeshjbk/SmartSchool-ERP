package com.raj.schoolerp.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class ExamSubjectsDTO {
	
	@NonNull
	private LocalDate examDate;
	
	@NonNull
	private Integer durationMin;
	
	@NonNull
	private Integer maxMarks;
	
	@NonNull
	private Integer passMarks;
	
	@NonNull
	private LocalTime startTime;
	
	@NonNull
	private String roomNo;
	
}
