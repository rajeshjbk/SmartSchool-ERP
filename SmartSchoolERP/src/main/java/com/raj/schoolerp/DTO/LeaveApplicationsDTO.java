package com.raj.schoolerp.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
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
	private String  leaveStatus ;
	
	@NonNull
	private String rejectionNote;
	
	private LocalDateTime appliedOn;
	
	private LocalDateTime approvedOn;
}
