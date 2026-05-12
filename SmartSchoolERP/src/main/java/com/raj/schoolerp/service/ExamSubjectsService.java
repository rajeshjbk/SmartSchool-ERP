package com.raj.schoolerp.service;

import java.time.LocalDate;
import java.util.List;

import com.raj.schoolerp.DTO.ExamSubjectsDTO;
import com.raj.schoolerp.exception.ExamSubjectsException;
import com.raj.schoolerp.model.ExamSubjects;

public interface ExamSubjectsService {

	// Add Exam Subject
	ExamSubjects addExamSubject(ExamSubjectsDTO examSubjectsDTO) throws ExamSubjectsException;

	// Update Exam Subject
	ExamSubjects updateExamSubject(Long examSubId, ExamSubjectsDTO examSubjectsDTO) throws ExamSubjectsException;

	// Delete Exam Subject
	String deleteExamSubject(Long examSubId) throws ExamSubjectsException;

	// Get Exam Subject By Id
	ExamSubjects getExamSubjectById(Long examSubId) throws ExamSubjectsException;

	// Get All Exam Subjects
	List<ExamSubjects> getAllExamSubjects() throws ExamSubjectsException;

	// Get Exam Subjects By Exam Id
	List<ExamSubjects> getExamSubjectsByExamId(Long examId) throws ExamSubjectsException;

	// Get Exam Subjects By Subject Id
	List<ExamSubjects> getExamSubjectsBySubjectId(Long subjectId) throws ExamSubjectsException;

	// Get Exam Subjects By Exam Date
	List<ExamSubjects> getExamSubjectsByDate(LocalDate examDate) throws ExamSubjectsException;

	// Get Exam Schedule By Exam Id
	List<ExamSubjects> getExamSchedule(Long examId) throws ExamSubjectsException;
}