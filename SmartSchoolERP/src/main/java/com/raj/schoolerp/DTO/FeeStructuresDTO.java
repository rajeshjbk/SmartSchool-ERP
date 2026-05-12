package com.raj.schoolerp.DTO;

import com.raj.schoolerp.model.Frequency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeeStructuresDTO {

	private Long classId;

	private String feeType;

	private Double amount;

	private Frequency frequency;

	private Integer dueDay;

	private String academicYear;

	private Boolean isMandatory;

	private Double lateFine;
}