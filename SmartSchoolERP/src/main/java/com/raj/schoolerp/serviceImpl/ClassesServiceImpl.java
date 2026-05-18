package com.raj.schoolerp.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.ClassesDTO;
import com.raj.schoolerp.exception.ClassesException;
import com.raj.schoolerp.model.Classes;
import com.raj.schoolerp.model.Teachers;
import com.raj.schoolerp.repository.ClassesRepository;
import com.raj.schoolerp.repository.TeachersRepository;
import com.raj.schoolerp.service.ClassesService;

@Service
public class ClassesServiceImpl implements ClassesService {

	@Autowired
	private ClassesRepository classesRepository;

	@Autowired
	private TeachersRepository teachersRepository;

	@Override
	public Classes addClass(ClassesDTO classesDTO) throws ClassesException {

		// Duplicate class check
		boolean exists = classesRepository.existsByClassNameAndSection(classesDTO.getClassName(),
				classesDTO.getSection());

		if (exists) {

			throw new ClassesException("Class already exists with section: " + classesDTO.getSection());
		}

		// Fetch Teacher
		Teachers teacher = null;

		if (classesDTO.getTeacherId() != null) {

			teacher = teachersRepository.findById(classesDTO.getTeacherId())
					.orElseThrow(() -> new ClassesException("Teacher not found with id: " + classesDTO.getTeacherId()));
		}

		Classes newClass = new Classes();

		BeanUtils.copyProperties(classesDTO, newClass);

		// Set relation
		newClass.setTeacher(teacher);

		return classesRepository.save(newClass);
	}

	@Override
	public Classes updateClass(Long classId, ClassesDTO updatedClassesDTO) throws ClassesException {

		Classes existingClass = classesRepository.findById(classId)
				.orElseThrow(() -> new ClassesException("Class not found with id: " + classId));

		// Fetch Teacher
		Teachers teacher = null;

		if (updatedClassesDTO.getTeacherId() != null) {

			teacher = teachersRepository.findById(updatedClassesDTO.getTeacherId()).orElseThrow(
					() -> new ClassesException("Teacher not found with id: " + updatedClassesDTO.getTeacherId()));
		}

		BeanUtils.copyProperties(updatedClassesDTO, existingClass);

		// Set relation
		existingClass.setTeacher(teacher);

		return classesRepository.save(existingClass);
	}

	@Override
	public void deleteClass(Long classId) throws ClassesException {

		Classes existingClass = classesRepository.findById(classId)
				.orElseThrow(() -> new ClassesException("Class not found with id: " + classId));

		classesRepository.delete(existingClass);
	}

	@Override
	public Classes getClassById(Long classId) throws ClassesException {

		return classesRepository.findById(classId)
				.orElseThrow(() -> new ClassesException("Class not found with id: " + classId));
	}

	@Override
	public List<Classes> getAllClasses() throws ClassesException {

		List<Classes> classes = classesRepository.findAll();

		if (classes.isEmpty()) {
			throw new ClassesException("No classes found");
		}

		return classes;
	}

	@Override
	public List<Classes> getClassByName(String className) throws ClassesException {

		List<Classes> classes = classesRepository.findByClassNameIgnoreCase(className);

		if (classes.isEmpty()) {
			throw new ClassesException("No class found with name: " + className);
		}

		return classes;
	}

	@Override
	public List<Classes> getClassBySection(String section) throws ClassesException {

		List<Classes> classes = classesRepository.findBySectionIgnoreCase(section);

		if (classes.isEmpty()) {
			throw new ClassesException("No class found for section: " + section);
		}

		return classes;
	}

	@Override
	public List<Classes> getClassesByAcademicYear(String academicYear) throws ClassesException {

		List<Classes> classes = classesRepository.findByAcademicYear(academicYear);

		if (classes.isEmpty()) {
			throw new ClassesException("No classes found for academic year: " + academicYear);
		}

		return classes;
	}

	@Override
	public Classes getClassByRoomNo(String roomNo) throws ClassesException {

		return classesRepository.findByRoomNo(roomNo)
				.orElseThrow(() -> new ClassesException("Class not found with room no: " + roomNo));
	}

	@Override
	public Classes getClassByTeacherId(Long teacherId) throws ClassesException {

		return classesRepository.findByTeacherTeacherId(teacherId)
				.orElseThrow(() -> new ClassesException("Class not found for teacher id: " + teacherId));
	}

	@Override
	public Classes getStudentsByClass(Long classId) throws ClassesException {

		return getClassById(classId);
	}

	@Override
	public Classes getSubjectsByClass(Long classId) throws ClassesException {

		return getClassById(classId);
	}

	@Override
	public Long countClasses() {

		return classesRepository.count();
	}

	@Override
	public boolean existsByClassNameAndSection(String className, String section) {

		return classesRepository.existsByClassNameAndSection(className, section);
	}
}