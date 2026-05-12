package com.raj.schoolerp.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamSubjectsDTO {

    private Long examId;

    private Long subjectId;

    private LocalDate examDate;

    private Integer durationMin;

    private Integer maxMarks;

    private Integer passMarks;

    private LocalTime startTime;

    private String roomNo;
}