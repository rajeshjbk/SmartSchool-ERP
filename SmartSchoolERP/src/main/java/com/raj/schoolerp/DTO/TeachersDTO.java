package com.raj.schoolerp.DTO;

import com.raj.schoolerp.model.TeacherStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeachersDTO {

    private String employeeId;

    private String department;

    private String designation;

    private Double salary;

    private String qualification;

    private TeacherStatus teacherStatus;

    private Long userId;
}