package com.raj.schoolerp.DTO;

import java.time.LocalDate;

import com.raj.schoolerp.model.LeaveStatus;
import com.raj.schoolerp.model.LeaveType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveApplicationsDTO {

    private LeaveType leaveType;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Integer totalDays;

    private String reason;

    private LeaveStatus leaveStatus;

    private Long userId;

    private Long approvedBy;

    private String rejectionNote;
}