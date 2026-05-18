package com.raj.schoolerp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.raj.schoolerp.DTO.LeaveApplicationsDTO;
import com.raj.schoolerp.exception.LeaveApplicationsException;
import com.raj.schoolerp.model.LeaveApplications;
import com.raj.schoolerp.model.LeaveStatus;
import com.raj.schoolerp.service.LeaveApplicationsService;

@RestController
@RequestMapping("/schoolerp/leave-applications")
@CrossOrigin(origins = "http://localhost:5173")
public class LeaveApplicationsController {

	@Autowired
	private LeaveApplicationsService leaveService;

	@PostMapping("/apply")
	public LeaveApplications applyLeave(@RequestBody LeaveApplicationsDTO leaveApplicationsDTO)
			throws LeaveApplicationsException {

		return leaveService.applyLeave(leaveApplicationsDTO);
	}

	@PutMapping("/update/{leaveId}")
	public LeaveApplications updateLeaveApplication(@PathVariable Long leaveId,
			@RequestBody LeaveApplicationsDTO leaveApplicationsDTO) throws LeaveApplicationsException {

		return leaveService.updateLeaveApplication(leaveId, leaveApplicationsDTO);
	}

	@GetMapping("/{leaveId}")
	public LeaveApplications getLeaveById(@PathVariable Long leaveId) throws LeaveApplicationsException {

		return leaveService.getLeaveById(leaveId);
	}

	@GetMapping("/all")
	public List<LeaveApplications> getAllLeaveApplications() throws LeaveApplicationsException {

		return leaveService.getAllLeaveApplications();
	}

	@GetMapping("/user/{userId}")
	public List<LeaveApplications> getLeaveApplicationsByUserId(@PathVariable Long userId)
			throws LeaveApplicationsException {

		return leaveService.getLeaveApplicationsByUserId(userId);
	}

	@GetMapping("/status/{leaveStatus}")
	public List<LeaveApplications> getLeaveApplicationsByStatus(@PathVariable LeaveStatus leaveStatus)
			throws LeaveApplicationsException {

		return leaveService.getLeaveApplicationsByStatus(leaveStatus);
	}
}