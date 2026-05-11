package com.raj.schoolerp.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.raj.schoolerp.DTO.AttendanceDTO;
import com.raj.schoolerp.entity.Attendance;
import com.raj.schoolerp.exception.AttendanceException;
import com.raj.schoolerp.service.AttendanceService;

@RestController
@RequestMapping("/schoolerp/attendance")
@CrossOrigin("*")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	// Mark Attendance
	@PostMapping("/mark")
	public Attendance markAttendance(@RequestBody AttendanceDTO attendanceDTO) throws AttendanceException {

		return attendanceService.markAttendance(attendanceDTO);
	}

	// Update Attendance
	@PutMapping("/update/{attendanceId}")
	public Attendance updateAttendance(@PathVariable Long attendanceId, @RequestBody AttendanceDTO attendanceDTO)
			throws AttendanceException {

		return attendanceService.updateAttendance(attendanceId, attendanceDTO);
	}

	// Delete Attendance
	@DeleteMapping("/delete/{attendanceId}")
	public String deleteAttendance(@PathVariable Long attendanceId) throws AttendanceException {

		return attendanceService.deleteAttendance(attendanceId);
	}

	// Get Attendance By Id
	@GetMapping("/{attendanceId}")
	public Attendance getAttendanceById(@PathVariable Long attendanceId) throws AttendanceException {

		return attendanceService.getAttendanceById(attendanceId);
	}

	// Get All Attendance
	@GetMapping("/all")
	public List<Attendance> getAllAttendance() throws AttendanceException {

		return attendanceService.getAllAttendance();
	}

	// Get Attendance By Student Id
	@GetMapping("/student/{studentId}")
	public List<Attendance> getAttendanceByStudentId(@PathVariable Long studentId) throws AttendanceException {

		return attendanceService.getAttendanceByStudentId(studentId);
	}

	// Get Attendance By Class Id
	@GetMapping("/class/{classId}")
	public List<Attendance> getAttendanceByClassId(@PathVariable Long classId) throws AttendanceException {

		return attendanceService.getAttendanceByClassId(classId);
	}

	// Get Attendance By Date
	@GetMapping("/date")
	public List<Attendance> getAttendanceByDate(@RequestParam Date date) throws AttendanceException {

		return attendanceService.getAttendanceByDate(date);
	}

	// Get Student Attendance By Date
	@GetMapping("/student-date")
	public Attendance getStudentAttendanceByDate(@RequestParam Long studentId, @RequestParam Date date)
			throws AttendanceException {

		return attendanceService.getStudentAttendanceByDate(studentId, date);
	}

	// Get Attendance Between Dates
	@GetMapping("/between-dates")
	public List<Attendance> getAttendanceBetweenDates(@RequestParam Date startDate, @RequestParam Date endDate)
			throws AttendanceException {

		return attendanceService.getAttendanceBetweenDates(startDate, endDate);
	}
}