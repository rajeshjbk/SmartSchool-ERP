package com.raj.schoolerp.service;

import java.util.List;

import com.raj.schoolerp.DTO.LeaveApplicationsDTO;
import com.raj.schoolerp.exception.LeaveApplicationsException;
import com.raj.schoolerp.model.LeaveApplications;
import com.raj.schoolerp.model.LeaveStatus;

public interface LeaveApplicationsService {

	// Apply Leave
	LeaveApplications applyLeave(LeaveApplicationsDTO leaveApplicationsDTO) throws LeaveApplicationsException;

	// Update Leave Application
	LeaveApplications updateLeaveApplication(Long leaveId, LeaveApplicationsDTO leaveApplicationsDTO)
			throws LeaveApplicationsException;

	// Get Leave By Id
	LeaveApplications getLeaveById(Long leaveId) throws LeaveApplicationsException;

	// Get All Leave Applications
	List<LeaveApplications> getAllLeaveApplications() throws LeaveApplicationsException;

	// Get Leave Applications By User Id
	List<LeaveApplications> getLeaveApplicationsByUserId(Long userId) throws LeaveApplicationsException;

	// Get Leave Applications By Status
	List<LeaveApplications> getLeaveApplicationsByStatus(LeaveStatus leaveStatus) throws LeaveApplicationsException;

	public List<LeaveApplications> getParentLeaves(Long parentId)throws LeaveApplicationsException;
	
	void deleteLeave(Long leaveId) throws LeaveApplicationsException;
}