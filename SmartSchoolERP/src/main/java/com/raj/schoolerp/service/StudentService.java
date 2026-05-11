package com.raj.schoolerp.service;

import java.util.List;

import com.raj.schoolerp.DTO.StudentsDTO;
import com.raj.schoolerp.entity.Students;
import com.raj.schoolerp.exception.StudentsException;

public interface StudentService {

	public Students addStudent(StudentsDTO studentsDTO) throws StudentsException;
	
	public Students getStudentByAdmissionNo(String admissionId) throws StudentsException;
	
	public Students getStudentByStudentId(Long studentId) throws StudentsException;
	
	public Students updateStudentByStudentId(Long studentId, StudentsDTO studentsDTO) throws StudentsException;
	
	public String deleteStudentByStudentId(Long studentId) throws StudentsException;
	
	public List<Students> getAllStudents() throws StudentsException;
	
}
