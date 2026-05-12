package com.raj.schoolerp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultsDTO {

    private Long studentId;

    private Long examSubjectId;

    private Double marksObtained;

    private String grade;

    private Double gradePoint;

    private Integer rankInClass;

    private Boolean isAbsent;

    private String remarks;
}