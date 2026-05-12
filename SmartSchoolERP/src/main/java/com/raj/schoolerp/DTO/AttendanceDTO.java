package com.raj.schoolerp.DTO;

import com.raj.schoolerp.model.AttendanceStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO {

    private Long studentId;

    private AttendanceStatus attendanceStatus;

    private String remarks;

    private Long markedBy;

    private Long classId;
}