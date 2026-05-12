package com.raj.schoolerp.DTO;

import java.time.LocalDate;

import com.raj.schoolerp.model.ExamStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamsDTO {

    private String examName;

    private Long classId;

    private String academicYear;

    private LocalDate endDate;

    private LocalDate resultDate;

    private ExamStatus examStatus;
}