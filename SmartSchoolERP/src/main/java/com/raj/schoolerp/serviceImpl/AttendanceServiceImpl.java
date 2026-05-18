package com.raj.schoolerp.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.AttendanceDTO;
import com.raj.schoolerp.exception.AttendanceException;
import com.raj.schoolerp.model.Attendance;
import com.raj.schoolerp.model.Classes;
import com.raj.schoolerp.model.Students;
import com.raj.schoolerp.model.Users;
import com.raj.schoolerp.repository.AttendanceRepository;
import com.raj.schoolerp.repository.ClassesRepository;
import com.raj.schoolerp.repository.StudentsRepository;
import com.raj.schoolerp.repository.UsersRepository;
import com.raj.schoolerp.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepo;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private ClassesRepository classesRepository;

	@Autowired
	private StudentsRepository studentsRepo;

	@Override
	public Attendance markAttendance(AttendanceDTO attendanceDTO) throws AttendanceException {

		// Fetch Student
		Students student = studentsRepo.findById(attendanceDTO.getStudentId()).orElseThrow(
				() -> new AttendanceException("Student not found with id: " + attendanceDTO.getStudentId()));

		// Fetch Marked By User
		Users markedByUser = usersRepository.findById(attendanceDTO.getMarkedBy())
				.orElseThrow(() -> new AttendanceException("User not found with id: " + attendanceDTO.getMarkedBy()));

		// Fetch Class
		Classes classes = classesRepository.findById(attendanceDTO.getClassId())
				.orElseThrow(() -> new AttendanceException("Class not found with id: " + attendanceDTO.getClassId()));

		Attendance attendance = new Attendance();

		BeanUtils.copyProperties(attendanceDTO, attendance);

		// Set Relations
		attendance.setStudents(student);

		attendance.setUser(markedByUser);

		attendance.setClasses(classes);

		return attendanceRepo.save(attendance);
	}

	@Override
	public Attendance updateAttendance(Long attendanceId, AttendanceDTO attendanceDTO) throws AttendanceException {

		Attendance existAttendance = attendanceRepo.findById(attendanceId)
				.orElseThrow(() -> new AttendanceException("Attendance Not Found"));

		// Fetch Student
		Students student = studentsRepo.findById(attendanceDTO.getStudentId())
				.orElseThrow(() -> new AttendanceException("Student not found"));

		// Fetch Marked By User
		Users markedByUser = usersRepository.findById(attendanceDTO.getMarkedBy())
				.orElseThrow(() -> new AttendanceException("Marked By user not found"));

		// Fetch Class
		Classes classes = classesRepository.findById(attendanceDTO.getClassId())
				.orElseThrow(() -> new AttendanceException("Class not found"));

		BeanUtils.copyProperties(attendanceDTO, existAttendance);

		// Set Relations
		existAttendance.setStudents(student);

		existAttendance.setUser(markedByUser);

		existAttendance.setClasses(classes);

		return attendanceRepo.save(existAttendance);
	}

	@Override
	public String deleteAttendance(Long attendanceId) throws AttendanceException {

		attendanceRepo.findById(attendanceId).orElseThrow(() -> new AttendanceException("Attendance Not Found"));

		attendanceRepo.deleteById(attendanceId);

		return "Attendance deleted with ID: " + attendanceId;
	}

	@Override
	public Attendance getAttendanceById(Long attendanceId) throws AttendanceException {

		return attendanceRepo.findById(attendanceId).orElseThrow(() -> new AttendanceException("Wrong Attendance Id"));
	}

	@Override
	public List<Attendance> getAllAttendance() throws AttendanceException {

		return attendanceRepo.findAll();
	}

	@Override
	public List<Attendance> getAttendanceByStudentId(Long studentId) throws AttendanceException {

		List<Attendance> attendance = attendanceRepo.findAttendanceByStudentId(studentId);

		if (attendance.isEmpty()) {

			throw new AttendanceException("No Attendance Found For This Student");
		}

		return attendance;
	}

	@Override
	public List<Attendance> getAttendanceByClassId(Long classId) throws AttendanceException {

		List<Attendance> attendance = attendanceRepo.findAttendanceByClassId(classId);

		if (attendance.isEmpty()) {

			throw new AttendanceException("No Attendance Found For This Class");
		}

		return attendance;
	}

	@Override
	public List<Attendance> getAttendanceByDate(Date date) throws AttendanceException {

		List<Attendance> attendance = attendanceRepo.findAttendanceByDate(date);

		if (attendance.isEmpty()) {

			throw new AttendanceException("No Attendance Found On This Date");
		}

		return attendance;
	}

	@Override
	public Attendance getStudentAttendanceByDate(Long studentId, Date date) throws AttendanceException {

		return attendanceRepo.findStudentAttendanceByDate(studentId, date)
				.orElseThrow(() -> new AttendanceException("Attendance Not Found"));
	}

	@Override
	public List<Attendance> getAttendanceBetweenDates(Date startDate, Date endDate) throws AttendanceException {

		List<Attendance> attendance = attendanceRepo.findAttendanceBetweenDates(startDate, endDate);

		if (attendance.isEmpty()) {

			throw new AttendanceException("No Attendance Found Between Dates");
		}

		return attendance;
	}
}