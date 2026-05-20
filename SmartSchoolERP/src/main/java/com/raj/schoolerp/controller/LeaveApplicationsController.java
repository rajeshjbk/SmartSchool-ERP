package com.raj.schoolerp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("/parent/{parentId}")
	public ResponseEntity<List<LeaveApplications>> getParentLeaves(@PathVariable Long parentId)
			throws LeaveApplicationsException {

		return new ResponseEntity<>(leaveService.getParentLeaves(parentId), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{leaveId}")
	public ResponseEntity<String> deleteLeave(@PathVariable Long leaveId) throws LeaveApplicationsException {

		leaveService.deleteLeave(leaveId);

		return new ResponseEntity<>("Leave application deleted successfully", HttpStatus.OK);
	}
}