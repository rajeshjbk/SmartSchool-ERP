package com.raj.schoolerp.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.SubjectsDTO;
import com.raj.schoolerp.exception.SubjectsException;
import com.raj.schoolerp.model.Classes;
import com.raj.schoolerp.model.Students;
import com.raj.schoolerp.model.Subjects;
import com.raj.schoolerp.model.Teachers;
import com.raj.schoolerp.model.Users;
import com.raj.schoolerp.repository.ClassesRepository;
import com.raj.schoolerp.repository.StudentsRepository;
import com.raj.schoolerp.repository.SubjectsRepository;
import com.raj.schoolerp.repository.TeachersRepository;
import com.raj.schoolerp.repository.UsersRepository;
import com.raj.schoolerp.service.SubjectsService;

@Service
public class SubjectsServiceImpl implements SubjectsService {

	@Autowired
	private SubjectsRepository subjectsRepo;

	@Autowired
	private UsersRepository usersRepo;

	@Autowired
	private StudentsRepository studentRepo;

	@Autowired
	private TeachersRepository teachersRepository;

	@Autowired
	private ClassesRepository classesRepository;

	@Override
	public Subjects addSubject(SubjectsDTO subjectDTO) throws SubjectsException {

		// Duplicate subject code check
		boolean exists = subjectsRepo.existsBySubjectCode(subjectDTO.getSubjectCode());

		if (exists) {

			throw new SubjectsException("Subject already exists with code: " + subjectDTO.getSubjectCode());
		}

		// Fetch Class
		Classes classes = null;

		if (subjectDTO.getClassId() != null) {

			classes = classesRepository.findById(subjectDTO.getClassId())
					.orElseThrow(() -> new SubjectsException("Class not found with id: " + subjectDTO.getClassId()));
		}

		// Fetch Teacher
		Teachers teacher = null;

		if (subjectDTO.getTeacherId() != null) {

			teacher = teachersRepository.findById(subjectDTO.getTeacherId()).orElseThrow(
					() -> new SubjectsException("Teacher not found with id: " + subjectDTO.getTeacherId()));
		}

		Subjects newSubject = new Subjects();

		BeanUtils.copyProperties(subjectDTO, newSubject);

		// Set relations
		newSubject.setClasses(classes);

		newSubject.setTeacher(teacher);

		return subjectsRepo.save(newSubject);
	}

	@Override
	public Subjects updateSubject(Long subjectId, SubjectsDTO subjectDTO) throws SubjectsException {

		Subjects existSubject = subjectsRepo.findById(subjectId)
				.orElseThrow(() -> new SubjectsException("Subject Not Found"));

		// Fetch Class
		Classes classes = null;

		if (subjectDTO.getClassId() != null) {

			classes = classesRepository.findById(subjectDTO.getClassId())
					.orElseThrow(() -> new SubjectsException("Class not found"));
		}

		// Fetch Teacher
		Teachers teacher = null;

		if (subjectDTO.getTeacherId() != null) {

			teacher = teachersRepository.findById(subjectDTO.getTeacherId())
					.orElseThrow(() -> new SubjectsException("Teacher not found"));
		}

		BeanUtils.copyProperties(subjectDTO, existSubject);

		// Set relations
		existSubject.setClasses(classes);

		existSubject.setTeacher(teacher);

		return subjectsRepo.save(existSubject);
	}

	@Override
	public String deleteSubject(Long subjectId) throws SubjectsException {

		subjectsRepo.findById(subjectId).orElseThrow(() -> new SubjectsException("Subject Not Found"));

		subjectsRepo.deleteById(subjectId);

		return "Subject is deleted with Subject ID: " + subjectId;
	}

	@Override
	public Subjects getSubjectById(Long subjectId) throws SubjectsException {

		return subjectsRepo.findById(subjectId).orElseThrow(() -> new SubjectsException("Wrong Subject Id"));
	}

	@Override
	public List<Subjects> getAllSubjects() throws SubjectsException {

		return subjectsRepo.findAll();
	}

	@Override
	public Subjects getSubjectByCode(String subjectCode) throws SubjectsException {

		return subjectsRepo.findBySubjectCode(subjectCode)
				.orElseThrow(() -> new SubjectsException("Wrong Subject Code"));
	}

	@Override
	public List<Subjects> getSubjectsByClassId(Long classId) throws SubjectsException {

		List<Subjects> subjects = subjectsRepo.findSubjectsByClassId(classId);

		if (subjects.isEmpty()) {

			throw new SubjectsException("No Subjects Found For This Class");
		}

		return subjects;
	}

	@Override
	public List<Subjects> getSubjectsByTeacherId(Long teacherId) throws SubjectsException {

		List<Subjects> subjects = subjectsRepo.findSubjectsByTeacherId(teacherId);

		if (subjects.isEmpty()) {

			throw new SubjectsException("No Subjects Found For This Teacher");
		}

		return subjects;
	}

	@Override
	public List<Subjects> getElectiveSubjects() throws SubjectsException {

		List<Subjects> subjects = subjectsRepo.findElectiveSubjects();

		if (subjects.isEmpty()) {

			throw new SubjectsException("No Elective Subjects Found");
		}

		return subjects;
	}

	@Override
	public List<Subjects> getCoreSubjects() throws SubjectsException {

		List<Subjects> subjects = subjectsRepo.findCoreSubjects();

		if (subjects.isEmpty()) {

			throw new SubjectsException("No Core Subjects Found");
		}

		return subjects;
	}

	@Override
	public List<Subjects> getMySubjects(String username) throws SubjectsException {
		// Get User
		Users user = usersRepo.findByUserName(username).orElseThrow(() -> new SubjectsException("User Not Found"));

		// Get Student
		Students student = studentRepo.findByUser(user).orElseThrow(() -> new SubjectsException("Student Not Found"));

		// Get Subjects by Class
		List<Subjects> subjects = subjectsRepo.findByClassesClassId(student.getClasses().getClassId());

		if (subjects.isEmpty()) {
			throw new SubjectsException("No subjects found");
		}

		return subjects;
	}
}