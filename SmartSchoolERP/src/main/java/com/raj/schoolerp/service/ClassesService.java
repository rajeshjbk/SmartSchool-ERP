package com.raj.schoolerp.service;

import java.util.List;

import com.raj.schoolerp.DTO.ClassesDTO;
import com.raj.schoolerp.exception.ClassesException;
import com.raj.schoolerp.model.Classes;

public interface ClassesService {

	// Add Class
	Classes addClass(ClassesDTO classesDTO) throws ClassesException;

	// Update Class
	Classes updateClass(Long classId, ClassesDTO classesDTO) throws ClassesException;

	// Delete Class
	void deleteClass(Long classId) throws ClassesException;

	// Get Class By Id
	Classes getClassById(Long classId) throws ClassesException;

	// Get All Classes
	List<Classes> getAllClasses() throws ClassesException;

	// Get Class By Name
	List<Classes> getClassByName(String className) throws ClassesException;

	// Get Class By Section
	List<Classes> getClassBySection(String section) throws ClassesException;

	// Get Classes By Academic Year
	List<Classes> getClassesByAcademicYear(String academicYear) throws ClassesException;

	// Get Classes By Room Number
	Classes getClassByRoomNo(String roomNo) throws ClassesException;

	// Get Class Teacher
	Classes getClassByTeacherId(Long teacherId) throws ClassesException;

	// Get Students In Particular Class
	Classes getStudentsByClass(Long classId) throws ClassesException;

	// Get Subjects In Particular Class
	Classes getSubjectsByClass(Long classId) throws ClassesException;

	// Count Total Classes
	Long countClasses();

	// Check Class Exists
	boolean existsByClassNameAndSection(String className, String section);
}
