package com.raj.schoolerp.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.raj.schoolerp.entity.LeaveStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveApplicationsDTO {

	@NonNull
	private String leaveType;
	
	@NonNull
	private LocalDate fromDate;
	
	@NonNull
	private LocalDate toDate;
	
	@NonNull
	private Integer totalDays;
	
	@NonNull
	private String reason;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private LeaveStatus leaveStatus ;
	
}
