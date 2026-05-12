package com.raj.schoolerp.service;

import java.time.LocalDate;
import java.util.List;

import com.raj.schoolerp.DTO.ExamsDTO;
import com.raj.schoolerp.exception.ExamsException;
import com.raj.schoolerp.model.ExamStatus;
import com.raj.schoolerp.model.Exams;

public interface ExamsService {

	// Add Exam
	Exams addExam(ExamsDTO examsDTO) throws ExamsException;

	// Update Exam
	Exams updateExam(Long examId, ExamsDTO examsDTO) throws ExamsException;

	// Delete Exam
	String deleteExam(Long examId) throws ExamsException;

	// Get Exam By Id
	Exams getExamById(Long examId) throws ExamsException;

	// Get All Exams
	List<Exams> getAllExams() throws ExamsException;

	// Get Exams By Class Id
	List<Exams> getExamsByClassId(Long classId) throws ExamsException;

	// Get Exams By Academic Year
	List<Exams> getExamsByAcademicYear(String academicYear) throws ExamsException;

	// Get Exams By Status
	List<Exams> getExamsByStatus(ExamStatus examStatus) throws ExamsException;

	// Get Exams Between Dates
	List<Exams> getExamsBetweenDates(LocalDate startDate, LocalDate endDate) throws ExamsException;

	// Get Upcoming Exams
	List<Exams> getUpcomingExams() throws ExamsException;
}