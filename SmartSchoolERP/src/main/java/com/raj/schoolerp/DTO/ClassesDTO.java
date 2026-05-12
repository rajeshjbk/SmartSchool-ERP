package com.raj.schoolerp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassesDTO {

    private String className;

    private String section;

    private String academicYear;

    private String roomNo;

    private Integer capacity;

    private Long teacherId;
}