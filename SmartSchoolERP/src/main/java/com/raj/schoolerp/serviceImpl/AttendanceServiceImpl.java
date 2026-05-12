package com.raj.schoolerp.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.AttendanceDTO;
import com.raj.schoolerp.exception.AttendanceException;
import com.raj.schoolerp.model.Attendance;
import com.raj.schoolerp.repository.AttendanceRepository;
import com.raj.schoolerp.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepo;

	// Mark Attendance
	@Override
	public Attendance markAttendance(AttendanceDTO attendanceDTO) throws AttendanceException {

		Attendance attendance = new Attendance();

		BeanUtils.copyProperties(attendanceDTO, attendance);

		return attendanceRepo.save(attendance);
	}

	// Update Attendance
	@Override
	public Attendance updateAttendance(Long attendanceId, AttendanceDTO attendanceDTO) throws AttendanceException {

		Attendance existAttendance = attendanceRepo.findById(attendanceId)
				.orElseThrow(() -> new AttendanceException("Attendance Not Found"));

		BeanUtils.copyProperties(attendanceDTO, existAttendance);

		return attendanceRepo.save(existAttendance);
	}

	// Delete Attendance
	@Override
	public String deleteAttendance(Long attendanceId) throws AttendanceException {

		attendanceRepo.findById(attendanceId).orElseThrow(() -> new AttendanceException("Attendance Not Found"));

		attendanceRepo.deleteById(attendanceId);

		return "Attendance deleted with ID: " + attendanceId;
	}

	// Get Attendance By Id
	@Override
	public Attendance getAttendanceById(Long attendanceId) throws AttendanceException {

		return attendanceRepo.findById(attendanceId).orElseThrow(() -> new AttendanceException("Wrong Attendance Id"));
	}

	// Get All Attendance
	@Override
	public List<Attendance> getAllAttendance() throws AttendanceException {

		return attendanceRepo.findAll();
	}

	// Get Attendance By Student Id
	@Override
	public List<Attendance> getAttendanceByStudentId(Long studentId) throws AttendanceException {

		List<Attendance> attendance = attendanceRepo.findAttendanceByStudentId(studentId);

		if (attendance.isEmpty()) {

			throw new AttendanceException("No Attendance Found For This Student");
		}

		return attendance;
	}

	// Get Attendance By Class Id
	@Override
	public List<Attendance> getAttendanceByClassId(Long classId) throws AttendanceException {

		List<Attendance> attendance = attendanceRepo.findAttendanceByClassId(classId);

		if (attendance.isEmpty()) {

			throw new AttendanceException("No Attendance Found For This Class");
		}

		return attendance;
	}

	// Get Attendance By Date
	@Override
	public List<Attendance> getAttendanceByDate(Date date) throws AttendanceException {

		List<Attendance> attendance = attendanceRepo.findAttendanceByDate(date);

		if (attendance.isEmpty()) {

			throw new AttendanceException("No Attendance Found On This Date");
		}

		return attendance;
	}

	// Get Student Attendance By Date
	@Override
	public Attendance getStudentAttendanceByDate(Long studentId, Date date) throws AttendanceException {

		return attendanceRepo.findStudentAttendanceByDate(studentId, date)
				.orElseThrow(() -> new AttendanceException("Attendance Not Found"));
	}

	// Get Attendance Between Dates
	@Override
	public List<Attendance> getAttendanceBetweenDates(Date startDate, Date endDate) throws AttendanceException {

		List<Attendance> attendance = attendanceRepo.findAttendanceBetweenDates(startDate, endDate);

		if (attendance.isEmpty()) {

			throw new AttendanceException("No Attendance Found Between Dates");
		}

		return attendance;
	}
}