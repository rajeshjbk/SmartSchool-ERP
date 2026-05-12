package com.raj.schoolerp.DTO;

import java.time.LocalDate;

import com.raj.schoolerp.model.Gender;
import com.raj.schoolerp.model.StudentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentsDTO {

    private Long userId;

    private Long parentId;

    private String admissionNo;

    private String fullName;

    private LocalDate dob;

    private Gender gender;

    private Long classId;

    private String academicYear;

    private StudentStatus studentStatus;
}