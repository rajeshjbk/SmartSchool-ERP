package com.raj.schoolerp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.entity.TeacherStatus;
import com.raj.schoolerp.entity.Teachers;
import com.raj.schoolerp.exception.TeachersException;
import com.raj.schoolerp.repository.TeachersRepository;
import com.raj.schoolerp.service.TeachersService;

@Service
public class TeachersServiceImpl implements TeachersService {

	@Autowired
	private TeachersRepository teacherRepository;

	// Add Teacher
	@Override
	public Teachers addTeacher(Teachers teacher) throws TeachersException {

		boolean exists = teacherRepository.existsByEmployeeId(teacher.getEmployeeId());

		if (exists) {
			throw new TeachersException("Teacher already exists with Employee Id: " + teacher.getEmployeeId());
		}

		return teacherRepository.save(teacher);
	}

	// Update Teacher
	@Override
	public Teachers updateTeacher(Long teacherId, Teachers updatedTeacher) throws TeachersException {

		Teachers teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new TeachersException("Teacher not found with id: " + teacherId));

		teacher.setDepartment(updatedTeacher.getDepartment());

		teacher.setDesignation(updatedTeacher.getDesignation());

		teacher.setSalary(updatedTeacher.getSalary());

		teacher.setQualification(updatedTeacher.getQualification());

		teacher.setTeacherStatus(updatedTeacher.getTeacherStatus());

		teacher.setUser(updatedTeacher.getUser());

		return teacherRepository.save(teacher);
	}

	// Delete Teacher
	@Override
	public void deleteTeacher(Long teacherId) throws TeachersException {

		Teachers teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new TeachersException("Teacher not found with id: " + teacherId));

		teacherRepository.delete(teacher);
	}

	// Get Teacher By Id
	@Override
	public Teachers getTeacherById(Long teacherId) throws TeachersException {

		return teacherRepository.findById(teacherId)
				.orElseThrow(() -> new TeachersException("Teacher not found with id: " + teacherId));
	}

	// Get All Teachers
	@Override
	public List<Teachers> getAllTeachers() throws TeachersException {

		List<Teachers> teachers = teacherRepository.findAll();

		if (teachers.isEmpty()) {
			throw new TeachersException("No teachers found");
		}

		return teachers;
	}

	// Get Teacher By Employee Id
	@Override
	public Teachers getTeacherByEmployeeId(String employeeId) throws TeachersException {

		return teacherRepository.findByEmployeeId(employeeId)
				.orElseThrow(() -> new TeachersException("Teacher not found with Employee Id: " + employeeId));
	}

	// Get Teachers By Department
	@Override
	public List<Teachers> getTeachersByDepartment(String department) throws TeachersException {

		List<Teachers> teachers = teacherRepository.findByDepartmentIgnoreCase(department);

		if (teachers.isEmpty()) {
			throw new TeachersException("No teachers found in department: " + department);
		}

		return teachers;
	}

	// Get Teachers By Designation
	@Override
	public List<Teachers> getTeachersByDesignation(String designation) throws TeachersException {

		return teacherRepository.findByDesignationIgnoreCase(designation);
	}

	// Get Teachers By Status
	@Override
	public List<Teachers> getTeachersByStatus(TeacherStatus status) {

		return teacherRepository.findByTeacherStatus(status);
	}

	// Update Teacher Status
	@Override
	public Teachers updateTeacherStatus(Long teacherId, TeacherStatus status) throws TeachersException {

		Teachers teacher = getTeacherById(teacherId);

		teacher.setTeacherStatus(status);

		return teacherRepository.save(teacher);
	}

	// Get Teacher By User Id
	@Override
	public Teachers getTeacherByUserId(Long userId) throws TeachersException {

		return teacherRepository.findByUserUserId(userId)
				.orElseThrow(() -> new TeachersException("Teacher not found with user id: " + userId));
	}

	// Search Teacher By Name
	@Override
	public List<Teachers> searchTeacherByName(String fullname) {

		return teacherRepository.findByUserFullNameContainingIgnoreCase(fullname);
	}

	// Get Teachers By Qualification
	@Override
	public List<Teachers> getTeachersByQualification(String qualification) {

		return teacherRepository.findByQualificationIgnoreCase(qualification);
	}

	// Count Teachers
	@Override
	public Long countTeachers() {

		return teacherRepository.count();
	}

	// Exists By Employee Id
	@Override
	public boolean existsByEmployeeId(String employeeId) {

		return teacherRepository.existsByEmployeeId(employeeId);
	}
}