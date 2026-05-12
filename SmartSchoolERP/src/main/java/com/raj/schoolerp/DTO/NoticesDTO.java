package com.raj.schoolerp.DTO;

import java.time.LocalDate;

import com.raj.schoolerp.model.Audience;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticesDTO {

	private String title;

	private String content;

	private Audience audience;

	private Long classId;

	private Long createdBy;

	private LocalDate expiryDate;

	private String attachment;

	private Boolean isUrgent;
}