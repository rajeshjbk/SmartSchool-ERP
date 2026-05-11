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
public class NoticesDTO {
	
	@NonNull
	private String title;
	
	@NonNull
	private String content;
	
	@NonNull
	private String audience;
		
	@NonNull
	private LocalDate expiryDate;
	
	private String attachment;
	
	private Boolean isUrgent;
}
