package com.raj.schoolerp.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.LeaveApplicationsDTO;
import com.raj.schoolerp.exception.LeaveApplicationsException;
import com.raj.schoolerp.model.LeaveApplications;
import com.raj.schoolerp.model.LeaveStatus;
import com.raj.schoolerp.repository.LeaveApplicationsRepository;
import com.raj.schoolerp.service.LeaveApplicationsService;

@Service
public class LeaveApplicationsServiceImpl implements LeaveApplicationsService {

	@Autowired
	private LeaveApplicationsRepository leaveRepo;

	@Override
	public LeaveApplications applyLeave(LeaveApplicationsDTO leaveApplicationsDTO) throws LeaveApplicationsException {

		LeaveApplications newLeave = new LeaveApplications();

		BeanUtils.copyProperties(leaveApplicationsDTO, newLeave);

		return leaveRepo.save(newLeave);
	}

	@Override
	public LeaveApplications updateLeaveApplication(Long leaveId, LeaveApplicationsDTO leaveApplicationsDTO)
			throws LeaveApplicationsException {

		LeaveApplications existLeave = leaveRepo.findById(leaveId)
				.orElseThrow(() -> new LeaveApplicationsException("Leave Application Not Found"));

		BeanUtils.copyProperties(leaveApplicationsDTO, existLeave);

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
}