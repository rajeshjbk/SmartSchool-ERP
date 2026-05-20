package com.raj.schoolerp.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.raj.schoolerp.DTO.TimetableDTO;
import com.raj.schoolerp.exception.TimetableException;
import com.raj.schoolerp.model.DayOfWeek;
import com.raj.schoolerp.model.Timetable;

public interface TimetableService {

	// Add Timetable
	Timetable addTimetable(TimetableDTO timetableDTO) throws TimetableException;

	// Update Timetable
	Timetable updateTimetable(Long timeTableId, TimetableDTO timetableDTO) throws TimetableException;

	// Delete Timetable
	String deleteTimetable(Long timeTableId) throws TimetableException;

	// Get Timetable By Id
	Timetable getTimetableById(Long timeTableId) throws TimetableException;

	// Get All Timetables
	List<Timetable> getAllTimetables() throws TimetableException;

	// Get Timetable By Class Id
	List<Timetable> getTimetableByClassId(Long classId) throws TimetableException;

	// Get Timetable By Teacher Id
	List<Timetable> getTimetableByTeacherId(Long teacherId) throws TimetableException;

	// Get Timetable By Subject Id
	List<Timetable> getTimetableBySubjectId(Long subjectId) throws TimetableException;

	// Get Timetable By Day
	List<Timetable> getTimetableByDay(DayOfWeek dayOfWeek) throws TimetableException;

	// Get Class Timetable By Day
	List<Timetable> getClassTimetableByDay(Long classId, DayOfWeek dayOfWeek) throws TimetableException;

	List<Timetable> getStudentTimetable(Long userId) throws TimetableException;

	List<Timetable> getParentTimetable(Long parentId) throws TimetableException;
}