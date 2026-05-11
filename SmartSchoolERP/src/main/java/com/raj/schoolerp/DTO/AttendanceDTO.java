package com.raj.schoolerp.DTO;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO {

	@NonNull
	private Date date;
	
	@NonNull
	private String attendanceStatus;
	
	@NonNull
	private String remarks;
	
	private LocalDateTime createdAt;
	
}
