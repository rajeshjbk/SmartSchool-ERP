package com.raj.schoolerp.DTO;

import java.time.LocalTime;
import java.util.List;

import com.raj.schoolerp.model.DayOfWeek;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimetableDTO {

	private List<Long> classIds;

	private Long subjectId;

	private Long teacherId;

	private DayOfWeek dayOfWeek;

	private Integer periodOfTime;

	private LocalTime startTime;

	private LocalTime endTime;

	private String roomNo;
}