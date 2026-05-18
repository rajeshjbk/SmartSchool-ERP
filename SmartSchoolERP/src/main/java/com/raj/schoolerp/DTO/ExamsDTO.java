package com.raj.schoolerp.DTO;

import java.time.LocalDate;

import com.raj.schoolerp.model.ExamStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamsDTO {

    private String examName;

    private Long classId;

    private String academicYear;

    private LocalDate startDate;
    
    private LocalDate endDate;

    private LocalDate resultDate;

    private ExamStatus examStatus;
}