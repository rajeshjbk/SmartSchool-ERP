package com.raj.schoolerp.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.StudentsDTO;
import com.raj.schoolerp.exception.StudentsException;
import com.raj.schoolerp.model.Gender;
import com.raj.schoolerp.model.StudentStatus;
import com.raj.schoolerp.model.Students;
import com.raj.schoolerp.repository.StudentsRepository;
import com.raj.schoolerp.service.StudentService;

@Service
public class StudentsServiceImpl implements StudentService {

	@Autowired
	private StudentsRepository studentsRepo;
	
	@Override
	public Students addStudent(StudentsDTO studentsDTO) throws StudentsException {
		
		Students newStudent = new Students();
		
		BeanUtils.copyProperties(studentsDTO, newStudent);
		
		return studentsRepo.save(newStudent);
	}

	@Override
	public Students getStudentByAdmissionNo(String admissionNo) throws StudentsException {
		
		Optional<Students> byAdmissionId = studentsRepo.findByAdmissionNo(admissionNo);
		
		if(byAdmissionId.isPresent()) {
			
			return byAdmissionId.get();
		}
		
		throw new StudentsException("Your Student Admission No is wrong, Try again with correct Admission No.");
	}

	@Override
	public Students getStudentByStudentId(Long studentId) throws StudentsException {
		
		
		return studentsRepo.findById(studentId).orElseThrow(()-> new StudentsException("Wrong Student Id."));
	}

	@Override
	public Students updateStudentByStudentId(Long studentId, StudentsDTO studentsDTO) throws StudentsException {
		
		Students existStudent = studentsRepo.findById(studentId).orElseThrow(()-> new StudentsException("Student Not Found"));
		
		BeanUtils.copyProperties(studentsDTO, existStudent);
		
		return studentsRepo.save(existStudent);
	}

	@Override
	public String deleteStudentByStudentId(Long studentId) throws StudentsException {
		
		studentsRepo.findById(studentId).orElseThrow(()-> new StudentsException("Student Not Found"));
		
		studentsRepo.deleteById(studentId);
		
		return "Student is deleted with Student ID: "+studentId;
	}

	@Override
	public List<Students> getAllStudents() throws StudentsException {
		
		return studentsRepo.findAll();
	}

}
