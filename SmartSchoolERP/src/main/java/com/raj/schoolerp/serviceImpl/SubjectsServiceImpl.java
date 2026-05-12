package com.raj.schoolerp.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.SubjectsDTO;
import com.raj.schoolerp.exception.SubjectsException;
import com.raj.schoolerp.model.Subjects;
import com.raj.schoolerp.repository.SubjectsRepository;
import com.raj.schoolerp.service.SubjectsService;

@Service
public class SubjectsServiceImpl implements SubjectsService {

	@Autowired
	private SubjectsRepository subjectsRepo;

	// Add Subject
	@Override
	public Subjects addSubject(SubjectsDTO subjectDTO) throws SubjectsException {

		Subjects newSubject = new Subjects();

		BeanUtils.copyProperties(subjectDTO, newSubject);

		return subjectsRepo.save(newSubject);
	}

	// Update Subject
	@Override
	public Subjects updateSubject(Long subjectId, SubjectsDTO subjectDTO) throws SubjectsException {

		Subjects existSubject = subjectsRepo.findById(subjectId)
				.orElseThrow(() -> new SubjectsException("Subject Not Found"));

		BeanUtils.copyProperties(subjectDTO, existSubject);

		return subjectsRepo.save(existSubject);
	}

	// Delete Subject
	@Override
	public String deleteSubject(Long subjectId) throws SubjectsException {

		subjectsRepo.findById(subjectId).orElseThrow(() -> new SubjectsException("Subject Not Found"));

		subjectsRepo.deleteById(subjectId);

		return "Subject is deleted with Subject ID: " + subjectId;
	}

	// Get Subject By Id
	@Override
	public Subjects getSubjectById(Long subjectId) throws SubjectsException {

		return subjectsRepo.findById(subjectId).orElseThrow(() -> new SubjectsException("Wrong Subject Id"));
	}

	// Get All Subjects
	@Override
	public List<Subjects> getAllSubjects() throws SubjectsException {

		return subjectsRepo.findAll();
	}

	// Get Subject By Subject Code
	@Override
	public Subjects getSubjectByCode(String subjectCode) throws SubjectsException {

		return subjectsRepo.findBySubjectCode(subjectCode)
				.orElseThrow(() -> new SubjectsException("Wrong Subject Code"));
	}

	// Get Subjects By Class Id
	@Override
	public List<Subjects> getSubjectsByClassId(Long classId) throws SubjectsException {

		List<Subjects> subjects = subjectsRepo.findSubjectsByClassId(classId);

		if (subjects.isEmpty()) {

			throw new SubjectsException("No Subjects Found For This Class");
		}

		return subjects;
	}

	// Get Subjects By Teacher Id
	@Override
	public List<Subjects> getSubjectsByTeacherId(Long teacherId) throws SubjectsException {

		List<Subjects> subjects = subjectsRepo.findSubjectsByTeacherId(teacherId);

		if (subjects.isEmpty()) {

			throw new SubjectsException("No Subjects Found For This Teacher");
		}

		return subjects;
	}

	// Get Elective Subjects
	@Override
	public List<Subjects> getElectiveSubjects() throws SubjectsException {

		List<Subjects> subjects = subjectsRepo.findElectiveSubjects();

		if (subjects.isEmpty()) {

			throw new SubjectsException("No Elective Subjects Found");
		}

		return subjects;
	}

	// Get Core Subjects
	@Override
	public List<Subjects> getCoreSubjects() throws SubjectsException {

		List<Subjects> subjects = subjectsRepo.findCoreSubjects();

		if (subjects.isEmpty()) {

			throw new SubjectsException("No Core Subjects Found");
		}

		return subjects;
	}
}