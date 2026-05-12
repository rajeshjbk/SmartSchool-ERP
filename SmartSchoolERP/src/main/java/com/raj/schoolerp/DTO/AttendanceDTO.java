package com.raj.schoolerp.DTO;

import com.raj.schoolerp.model.AttendanceStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDTO {

	@NonNull
	@Enumerated(EnumType.STRING)
	private AttendanceStatus attendanceStatus;
	
	@NonNull
	private String remarks;
}
