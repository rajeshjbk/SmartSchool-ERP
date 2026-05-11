package com.raj.schoolerp.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.ClassesDTO;
import com.raj.schoolerp.entity.Classes;
import com.raj.schoolerp.exception.ClassesException;
import com.raj.schoolerp.repository.ClassesRepository;
import com.raj.schoolerp.service.ClassesService;

@Service
public class ClassesServiceImpl implements ClassesService {

	@Autowired
	private ClassesRepository classesRepository;

	// Add Class
	@Override
	public Classes addClass(ClassesDTO classesDTO) throws ClassesException {

		boolean exists = classesRepository.existsByClassNameAndSection(classesDTO.getClassName(), classesDTO.getSection());

		if (exists) {
			throw new ClassesException("Class already exists with section: " + classesDTO.getSection());
		}

		Classes newClass = new Classes();
		
		BeanUtils.copyProperties(classesDTO, newClass);
		
		return classesRepository.save(newClass);
	}

	// Update Class
	@Override
	public Classes updateClass(Long classId, ClassesDTO updatedClassesDTO) throws ClassesException {

		Classes existingClass = classesRepository.findById(classId)
				.orElseThrow(() -> new ClassesException("Class not found with id: " + classId));

		BeanUtils.copyProperties(updatedClassesDTO,existingClass);

		return classesRepository.save(existingClass);
	}

	// Delete Class
	@Override
	public void deleteClass(Long classId) throws ClassesException {

		Classes existingClass = classesRepository.findById(classId)
				.orElseThrow(() -> new ClassesException("Class not found with id: " + classId));

		classesRepository.delete(existingClass);
	}

	// Get Class By Id
	@Override
	public Classes getClassById(Long classId) throws ClassesException {

		return classesRepository.findById(classId)
				.orElseThrow(() -> new ClassesException("Class not found with id: " + classId));
	}

	// Get All Classes
	@Override
	public List<Classes> getAllClasses() throws ClassesException {

		List<Classes> classes = classesRepository.findAll();

		if (classes.isEmpty()) {
			throw new ClassesException("No classes found");
		}

		return classes;
	}

	// Get Class By Name
	@Override
	public List<Classes> getClassByName(String className) throws ClassesException {

		List<Classes> classes = classesRepository.findByClassNameIgnoreCase(className);

		if (classes.isEmpty()) {
			throw new ClassesException("No class found with name: " + className);
		}

		return classes;
	}

	// Get Class By Section
	@Override
	public List<Classes> getClassBySection(String section) throws ClassesException {

		List<Classes> classes = classesRepository.findBySectionIgnoreCase(section);

		if (classes.isEmpty()) {
			throw new ClassesException("No class found for section: " + section);
		}

		return classes;
	}

	// Get Classes By Academic Year
	@Override
	public List<Classes> getClassesByAcademicYear(String academicYear) throws ClassesException {

		List<Classes> classes = classesRepository.findByAcademicYear(academicYear);

		if (classes.isEmpty()) {
			throw new ClassesException("No classes found for academic year: " + academicYear);
		}

		return classes;
	}

	// Get Class By Room Number
	@Override
	public Classes getClassByRoomNo(String roomNo) throws ClassesException {

		return classesRepository.findByRoomNo(roomNo)
				.orElseThrow(() -> new ClassesException("Class not found with room no: " + roomNo));
	}

	// Get Class By Teacher Id
	@Override
	public Classes getClassByTeacherId(Long teacherId) throws ClassesException {

		return classesRepository.findByTeacherTeacherId(teacherId)
				.orElseThrow(() -> new ClassesException("Class not found for teacher id: " + teacherId));
	}

	// Get Students By Class
	@Override
	public Classes getStudentsByClass(Long classId) throws ClassesException {

		return getClassById(classId);
	}

	// Get Subjects By Class
	@Override
	public Classes getSubjectsByClass(Long classId) throws ClassesException {

		return getClassById(classId);
	}

	// Count Total Classes
	@Override
	public Long countClasses() {

		return classesRepository.count();
	}

	// Check Class Exists
	@Override
	public boolean existsByClassNameAndSection(String className, String section) {

		return classesRepository.existsByClassNameAndSection(className, section);
	}
}