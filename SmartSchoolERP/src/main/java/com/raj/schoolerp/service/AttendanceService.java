package com.raj.schoolerp.service;

import java.util.Date;
import java.util.List;

import com.raj.schoolerp.DTO.AttendanceDTO;
import com.raj.schoolerp.exception.AttendanceException;
import com.raj.schoolerp.model.Attendance;

public interface AttendanceService {

	// Mark Attendance
	Attendance markAttendance(AttendanceDTO attendanceDTO) throws AttendanceException;

	// Update Attendance
	Attendance updateAttendance(Long attendanceId, AttendanceDTO attendanceDTO) throws AttendanceException;

	// Delete Attendance
	String deleteAttendance(Long attendanceId) throws AttendanceException;

	// Get Attendance By Id
	Attendance getAttendanceById(Long attendanceId) throws AttendanceException;

	// Get All Attendance
	List<Attendance> getAllAttendance() throws AttendanceException;

	// Get Attendance By Student Id
	List<Attendance> getAttendanceByStudentId(Long studentId) throws AttendanceException;

	// Get Attendance By Class Id
	List<Attendance> getAttendanceByClassId(Long classId) throws AttendanceException;

	// Get Attendance By Date
	List<Attendance> getAttendanceByDate(Date date) throws AttendanceException;

	// Get Student Attendance By Date
	Attendance getStudentAttendanceByDate(Long studentId, Date date) throws AttendanceException;

	// Get Attendance Between Dates
	List<Attendance> getAttendanceBetweenDates(Date startDate, Date endDate) throws AttendanceException;
}