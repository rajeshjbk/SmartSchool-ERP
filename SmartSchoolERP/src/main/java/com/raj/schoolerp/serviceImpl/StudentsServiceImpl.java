package com.raj.schoolerp.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.StudentsDTO;
import com.raj.schoolerp.exception.StudentsException;
import com.raj.schoolerp.model.Classes;
import com.raj.schoolerp.model.Students;
import com.raj.schoolerp.model.UserRole;
import com.raj.schoolerp.model.Users;
import com.raj.schoolerp.repository.ClassesRepository;
import com.raj.schoolerp.repository.StudentsRepository;
import com.raj.schoolerp.repository.UsersRepository;
import com.raj.schoolerp.service.StudentService;

@Service
public class StudentsServiceImpl implements StudentService {

	@Autowired
	private StudentsRepository studentsRepo;

	@Autowired
	private ClassesRepository classesRepository;

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public Students addStudent(StudentsDTO studentsDTO) throws StudentsException {

		// Check duplicate student
		boolean exists = studentsRepo.existsByUser_UserId(studentsDTO.getUserId());

		if (exists) {

			throw new StudentsException("Student already registered");
		}

		// Fetch Student User
		Users studentUser = usersRepository.findById(studentsDTO.getUserId())
				.orElseThrow(() -> new StudentsException("Student user not found with id: " + studentsDTO.getUserId()));

		// Fetch Parent User
		Users parentUser = null;

		if (studentsDTO.getParentId() != null) {

			parentUser = usersRepository.findById(studentsDTO.getParentId())
					.orElseThrow(() -> new StudentsException("Parent not found with id: " + studentsDTO.getParentId()));
		}

		// Fetch Class
		Classes classes = classesRepository.findById(studentsDTO.getClassId())
				.orElseThrow(() -> new StudentsException("Class not found with id: " + studentsDTO.getClassId()));

		// Create Student
		Students newStudent = new Students();

		BeanUtils.copyProperties(studentsDTO, newStudent);

		// Set relations
		newStudent.setUser(studentUser);

		newStudent.setParent(parentUser);

		newStudent.setClasses(classes);

		return studentsRepo.save(newStudent);
	}

	@Override
	public Students getStudentByAdmissionNo(String admissionNo) throws StudentsException {

		Optional<Students> byAdmissionId = studentsRepo.findByAdmissionNo(admissionNo);

		if (byAdmissionId.isPresent()) {

			return byAdmissionId.get();
		}

		throw new StudentsException("Your Student Admission No is wrong, Try again with correct Admission No.");
	}

	@Override
	public Students getStudentByStudentId(Long studentId) throws StudentsException {

		return studentsRepo.findById(studentId).orElseThrow(() -> new StudentsException("Wrong Student Id."));
	}

	@Override
	public Students updateStudentByStudentId(Long studentId, StudentsDTO studentsDTO) throws StudentsException {

		// Existing student
		Students existStudent = studentsRepo.findById(studentId)
				.orElseThrow(() -> new StudentsException("Student Not Found"));

		// Fetch User
		Users studentUser = usersRepository.findById(studentsDTO.getUserId())
				.orElseThrow(() -> new StudentsException("Student User Not Found"));

		// Fetch Parent
		Users parentUser = null;

		if (studentsDTO.getParentId() != null) {

			parentUser = usersRepository.findById(studentsDTO.getParentId())
					.orElseThrow(() -> new StudentsException("Parent Not Found"));
		}

		// Fetch Class
		Classes classes = classesRepository.findById(studentsDTO.getClassId())
				.orElseThrow(() -> new StudentsException("Class Not Found"));

		// Copy simple fields
		BeanUtils.copyProperties(studentsDTO, existStudent);

		// Set Relations
		existStudent.setUser(studentUser);

		existStudent.setParent(parentUser);

		existStudent.setClasses(classes);

		return studentsRepo.save(existStudent);
	}

	@Override
	public String deleteStudentByStudentId(Long studentId) throws StudentsException {

		studentsRepo.findById(studentId).orElseThrow(() -> new StudentsException("Student Not Found"));

		studentsRepo.deleteById(studentId);

		return "Student is deleted with Student ID: " + studentId;
	}

	@Override
	public List<Students> getAllStudents() throws StudentsException {

		return studentsRepo.findAll();
	}

	@Override
	public List<Students> getStudentByRole(UserRole role) throws StudentsException {

		List<Students> students = studentsRepo.getStudentByRole(role);

		if (students.isEmpty()) {

			throw new StudentsException("No Students Found");
		}

		return students;
	}

	@Override
	public List<Students> getStudentByParentId(Long parentId) {

		List<Students> students = studentsRepo.getStudentByParentId(parentId);

		if (students.isEmpty()) {
			throw new RuntimeException("No child found");
		}

		return students;
	}
}
