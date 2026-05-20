package com.raj.schoolerp.service;

import java.util.List;

import com.raj.schoolerp.DTO.SubjectsDTO;
import com.raj.schoolerp.exception.SubjectsException;
import com.raj.schoolerp.model.Subjects;

public interface SubjectsService {

	// Add Subject
	Subjects addSubject(SubjectsDTO subjectDTO) throws SubjectsException;

	// Update Subject
	Subjects updateSubject(Long subjectId, SubjectsDTO subjectDTO) throws SubjectsException;

	// Delete Subject
	String deleteSubject(Long subjectId) throws SubjectsException;

	// Get Subject By Id
	Subjects getSubjectById(Long subjectId) throws SubjectsException;

	// Get All Subjects
	List<Subjects> getAllSubjects() throws SubjectsException;

	// Get Subject By Subject Code
	Subjects getSubjectByCode(String subjectCode) throws SubjectsException;

	// Get Subjects By Class Id
	List<Subjects> getSubjectsByClassId(Long classId) throws SubjectsException;

	// Get Subjects By Teacher Id
	List<Subjects> getSubjectsByTeacherId(Long teacherId) throws SubjectsException;

	// Get Elective Subjects
	List<Subjects> getElectiveSubjects() throws SubjectsException;

	// Get Core Subjects
	List<Subjects> getCoreSubjects() throws SubjectsException;

	List<Subjects> getMySubjects(String username) throws SubjectsException;
}