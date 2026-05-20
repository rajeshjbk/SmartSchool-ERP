package com.raj.schoolerp.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raj.schoolerp.DTO.AttendanceDTO;
import com.raj.schoolerp.exception.AttendanceException;
import com.raj.schoolerp.model.Attendance;
import com.raj.schoolerp.service.AttendanceService;

@RestController
@RequestMapping("/schoolerp/attendance")
@CrossOrigin(origins = "http://localhost:5173")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	@PostMapping("/mark")
	public Attendance markAttendance(@RequestBody AttendanceDTO attendanceDTO) throws AttendanceException {

		return attendanceService.markAttendance(attendanceDTO);
	}

	@PutMapping("/update/{attendanceId}")
	public Attendance updateAttendance(@PathVariable Long attendanceId, @RequestBody AttendanceDTO attendanceDTO)
			throws AttendanceException {

		return attendanceService.updateAttendance(attendanceId, attendanceDTO);
	}

	@DeleteMapping("/delete/{attendanceId}")
	public String deleteAttendance(@PathVariable Long attendanceId) throws AttendanceException {

		return attendanceService.deleteAttendance(attendanceId);
	}

	@GetMapping("/{attendanceId}")
	public Attendance getAttendanceById(@PathVariable Long attendanceId) throws AttendanceException {

		return attendanceService.getAttendanceById(attendanceId);
	}

	@GetMapping("/all")
	public List<Attendance> getAllAttendance() throws AttendanceException {

		return attendanceService.getAllAttendance();
	}

	// Get Attendance By Student Id
	@GetMapping("/student/{studentId}")
	public List<Attendance> getAttendanceByStudentId(@PathVariable Long studentId) throws AttendanceException {

		return attendanceService.getAttendanceByStudentId(studentId);
	}

	@GetMapping("/class/{classId}")
	public List<Attendance> getAttendanceByClassId(@PathVariable Long classId) throws AttendanceException {

		return attendanceService.getAttendanceByClassId(classId);
	}

	// Get Attendance By Date
	@GetMapping("/date")
	public List<Attendance> getAttendanceByDate(@RequestParam Date date) throws AttendanceException {

		return attendanceService.getAttendanceByDate(date);
	}

	@GetMapping("/student-date")
	public Attendance getStudentAttendanceByDate(@RequestParam Long studentId, @RequestParam Date date)
			throws AttendanceException {

		return attendanceService.getStudentAttendanceByDate(studentId, date);
	}

	@GetMapping("/between-dates")
	public List<Attendance> getAttendanceBetweenDates(@RequestParam Date startDate, @RequestParam Date endDate)
			throws AttendanceException {

		return attendanceService.getAttendanceBetweenDates(startDate, endDate);
	}

	@GetMapping("/parent/{parentId}")
	public ResponseEntity<List<Attendance>> getAttendanceByParentHandler(@PathVariable Long parentId) throws AttendanceException {

		List<Attendance> attendance = attendanceService.getAttendanceByParent(parentId);

		return new ResponseEntity<>(attendance, HttpStatus.OK);
	}
}