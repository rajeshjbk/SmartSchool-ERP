package com.raj.schoolerp.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.StudentsDTO;
import com.raj.schoolerp.entity.Gender;
import com.raj.schoolerp.entity.StudentStatus;
import com.raj.schoolerp.entity.Students;
import com.raj.schoolerp.exception.StudentsException;
import com.raj.schoolerp.repository.StudentsRepository;
import com.raj.schoolerp.service.StudentService;

@Service
public class StudentsServiceImpl implements StudentService {

	@Autowired
	private StudentsRepository studentsRepo;
	
	@Override
	public Students addStudent(StudentsDTO studentsDTO) throws StudentsException {
		
		Students newStudent = new Students();
		
		newStudent.setAdmissionNo(studentsDTO.getAdmissionNo());
		newStudent.setFullName(studentsDTO.getFullName());
		newStudent.setDob(studentsDTO.getDob());
		newStudent.setAcademicYear(studentsDTO.getAcademicYear());
		newStudent.setAdmissionDate(studentsDTO.getAdmissionDate());
		newStudent.setGender(Gender.valueOf(studentsDTO.getGender()));
		newStudent.setStudentStatus(StudentStatus.valueOf(studentsDTO.getStudentStatus()));
		
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
		
		existStudent.setAdmissionNo(studentsDTO.getAdmissionNo());
		existStudent.setFullName(studentsDTO.getFullName());
		existStudent.setDob(studentsDTO.getDob());
		existStudent.setAcademicYear(studentsDTO.getAcademicYear());
		existStudent.setAdmissionDate(studentsDTO.getAdmissionDate());
		existStudent.setGender(Gender.valueOf(studentsDTO.getGender()));
		existStudent.setStudentStatus(StudentStatus.valueOf(studentsDTO.getStudentStatus()));
		
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
