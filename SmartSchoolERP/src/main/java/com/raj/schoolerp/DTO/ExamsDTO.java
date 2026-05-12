package com.raj.schoolerp.DTO;

import java.time.LocalDate;

import com.raj.schoolerp.model.ExamStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	private LocalDate startDate;
	
	@NonNull
	private LocalDate endDate;
	
	private LocalDate resultDate;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private ExamStatus examStatus;
	
}
