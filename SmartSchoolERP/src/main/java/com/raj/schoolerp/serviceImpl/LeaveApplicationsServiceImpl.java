package com.raj.schoolerp.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.LeaveApplicationsDTO;
import com.raj.schoolerp.exception.LeaveApplicationsException;
import com.raj.schoolerp.model.LeaveApplications;
import com.raj.schoolerp.model.LeaveStatus;
import com.raj.schoolerp.model.Users;
import com.raj.schoolerp.repository.LeaveApplicationsRepository;
import com.raj.schoolerp.repository.UsersRepository;
import com.raj.schoolerp.service.LeaveApplicationsService;

@Service
public class LeaveApplicationsServiceImpl implements LeaveApplicationsService {

	@Autowired
	private LeaveApplicationsRepository leaveRepo;

	@Autowired
	private UsersRepository usersRepo;

	@Override
	public LeaveApplications applyLeave(LeaveApplicationsDTO leaveApplicationsDTO) throws LeaveApplicationsException {

		LeaveApplications newLeave = new LeaveApplications();

		BeanUtils.copyProperties(leaveApplicationsDTO, newLeave, "user", "approvedById", "leaveStatus");

		// Default Leave Status
		newLeave.setLeaveStatus(LeaveStatus.PENDING);

		// User Mapping
		Users user = usersRepo.findById(leaveApplicationsDTO.getUserId())
				.orElseThrow(() -> new LeaveApplicationsException("User Not Found"));

		newLeave.setUser(user);

		// Approved By (Optional)
		if (leaveApplicationsDTO.getApprovedById() != null) {

			Users approvedUser = usersRepo.findById(leaveApplicationsDTO.getApprovedById())
					.orElseThrow(() -> new LeaveApplicationsException("Approver Not Found"));

			newLeave.setApprovedById(approvedUser);
		} else {

			// Student Apply Leave
			newLeave.setApprovedById(null);
		}

		// Default rejection note
		newLeave.setRejectionNote(null);

		return leaveRepo.save(newLeave);
	}

	/*@Override
	public LeaveApplications applyLeave(LeaveApplicationsDTO leaveApplicationsDTO) throws LeaveApplicationsException {
	
		LeaveApplications newLeave = new LeaveApplications();
	
		BeanUtils.copyProperties(leaveApplicationsDTO, newLeave, "user", "approvedById");
	
		// User
		Users user = usersRepo.findById(leaveApplicationsDTO.getUserId())
				.orElseThrow(() -> new LeaveApplicationsException("User Not Found"));
	
		newLeave.setUser(user);
	
		// Approved By (Optional)
		if (leaveApplicationsDTO.getApprovedById() != null) {
	
			Users approvedUser = usersRepo.findById(leaveApplicationsDTO.getApprovedById())
					.orElseThrow(() -> new LeaveApplicationsException("Approver Not Found"));
	
			newLeave.setApprovedById(approvedUser);
		}
	
		return leaveRepo.save(newLeave);
	}*/

	@Override
	public LeaveApplications updateLeaveApplication(Long leaveId, LeaveApplicationsDTO leaveApplicationsDTO)
			throws LeaveApplicationsException {

		LeaveApplications existLeave = leaveRepo.findById(leaveId)
				.orElseThrow(() -> new LeaveApplicationsException("Leave Application Not Found"));

		BeanUtils.copyProperties(leaveApplicationsDTO, existLeave, "user", "approvedById");

		// User
		Users user = usersRepo.findById(leaveApplicationsDTO.getUserId())
				.orElseThrow(() -> new LeaveApplicationsException("User Not Found"));

		existLeave.setUser(user);

		// Approved By
		if (leaveApplicationsDTO.getApprovedById() != null) {

			Users approvedUser = usersRepo.findById(leaveApplicationsDTO.getApprovedById())
					.orElseThrow(() -> new LeaveApplicationsException("Approver Not Found"));

			existLeave.setApprovedById(approvedUser);
		} else {
			existLeave.setApprovedById(null);
		}

		return leaveRepo.save(existLeave);
	}

	@Override
	public LeaveApplications getLeaveById(Long leaveId) throws LeaveApplicationsException {

		return leaveRepo.findById(leaveId).orElseThrow(() -> new LeaveApplicationsException("Wrong Leave Id"));
	}

	@Override
	public List<LeaveApplications> getAllLeaveApplications() throws LeaveApplicationsException {

		return leaveRepo.findAll();
	}

	@Override
	public List<LeaveApplications> getLeaveApplicationsByUserId(Long userId) throws LeaveApplicationsException {

		List<LeaveApplications> leaveApplications = leaveRepo.findLeaveApplicationsByUserId(userId);

		if (leaveApplications.isEmpty()) {

			throw new LeaveApplicationsException("No Leave Applications Found");
		}

		return leaveApplications;
	}

	@Override
	public List<LeaveApplications> getLeaveApplicationsByStatus(LeaveStatus leaveStatus)
			throws LeaveApplicationsException {

		List<LeaveApplications> leaveApplications = leaveRepo.findLeaveApplicationsByStatus(leaveStatus);

		if (leaveApplications.isEmpty()) {

			throw new LeaveApplicationsException("No Leave Applications Found");
		}

		return leaveApplications;
	}

	@Override
	public List<LeaveApplications> getParentLeaves(Long parentId) throws LeaveApplicationsException {

		List<LeaveApplications> leaves = leaveRepo.getParentLeaves(parentId);

		if (leaves == null || leaves.isEmpty()) {
			return new ArrayList<>();
		}

		return leaves;
	}

	@Override
	public void deleteLeave(Long leaveId) throws LeaveApplicationsException {

		LeaveApplications leave = leaveRepo.findById(leaveId)
				.orElseThrow(() -> new LeaveApplicationsException("Leave application not found with ID: " + leaveId));

		leaveRepo.delete(leave);
	}
}