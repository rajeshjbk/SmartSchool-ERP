package com.raj.schoolerp.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.TeachersDTO;
import com.raj.schoolerp.exception.TeachersException;
import com.raj.schoolerp.model.TeacherStatus;
import com.raj.schoolerp.model.Teachers;
import com.raj.schoolerp.model.Users;
import com.raj.schoolerp.repository.TeachersRepository;
import com.raj.schoolerp.repository.UsersRepository;
import com.raj.schoolerp.service.TeachersService;

@Service
public class TeachersServiceImpl implements TeachersService {

	@Autowired
	private TeachersRepository teacherRepository;

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public Teachers addTeacher(TeachersDTO teacherDTO) throws TeachersException {

		// Check employeeId
		boolean exists = teacherRepository.existsByEmployeeId(teacherDTO.getEmployeeId());

		if (exists) {

			throw new TeachersException("Teacher already exists with Employee Id: " + teacherDTO.getEmployeeId());
		}

		// Get selected user
		Users user = usersRepository.findById(teacherDTO.getUserId())
				.orElseThrow(() -> new TeachersException("User not found with id: " + teacherDTO.getUserId()));

		// Check if teacher already linked
		boolean teacherExists = teacherRepository.existsByUser_UserId(teacherDTO.getUserId());

		if (teacherExists) {

			throw new TeachersException("Teacher already registered for User Id: " + teacherDTO.getUserId());
		}

		Teachers teacher = new Teachers();

		BeanUtils.copyProperties(teacherDTO, teacher);

		// Set User Object
		teacher.setUser(user);

		return teacherRepository.save(teacher);
	}

	@Override
	public Teachers updateTeacher(Long teacherId, TeachersDTO updatedTeacherDTO) throws TeachersException {

		Teachers teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new TeachersException("Teacher not found with id: " + teacherId));

		// Get User
		Users user = usersRepository.findById(updatedTeacherDTO.getUserId())
				.orElseThrow(() -> new TeachersException("User not found with id: " + updatedTeacherDTO.getUserId()));

		BeanUtils.copyProperties(updatedTeacherDTO, teacher);

		teacher.setUser(user);

		return teacherRepository.save(teacher);
	}

	@Override
	public void deleteTeacher(Long teacherId) throws TeachersException {

		Teachers teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new TeachersException("Teacher not found with id: " + teacherId));

		teacherRepository.delete(teacher);
	}

	@Override
	public Teachers getTeacherById(Long teacherId) throws TeachersException {

		return teacherRepository.findById(teacherId)
				.orElseThrow(() -> new TeachersException("Teacher not found with id: " + teacherId));
	}

	@Override
	public List<Teachers> getAllTeachers() throws TeachersException {

		List<Teachers> teachers = teacherRepository.findAll();

		if (teachers.isEmpty()) {
			throw new TeachersException("No teachers found");
		}

		return teachers;
	}

	@Override
	public Teachers getTeacherByEmployeeId(String employeeId) throws TeachersException {

		return teacherRepository.findByEmployeeId(employeeId)
				.orElseThrow(() -> new TeachersException("Teacher not found with Employee Id: " + employeeId));
	}

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

	@Override
	public Teachers updateTeacherStatus(Long teacherId, TeacherStatus status) throws TeachersException {

		Teachers teacher = getTeacherById(teacherId);

		teacher.setTeacherStatus(status);

		return teacherRepository.save(teacher);
	}

	@Override
	public Teachers getTeacherByUserId(Long userId) throws TeachersException {

		return teacherRepository.findByUserUserId(userId)
				.orElseThrow(() -> new TeachersException("Teacher not found with user id: " + userId));
	}

	@Override
	public List<Teachers> searchTeacherByName(String fullname) {

		return teacherRepository.findByUserFullNameContainingIgnoreCase(fullname);
	}

	@Override
	public List<Teachers> getTeachersByQualification(String qualification) {

		return teacherRepository.findByQualificationIgnoreCase(qualification);
	}

	@Override
	public Long countTeachers() {

		return teacherRepository.count();
	}

	@Override
	public boolean existsByEmployeeId(String employeeId) {

		return teacherRepository.existsByEmployeeId(employeeId);
	}
}