package com.raj.schoolerp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.raj.schoolerp.DTO.SubjectsDTO;
import com.raj.schoolerp.exception.SubjectsException;
import com.raj.schoolerp.model.Subjects;
import com.raj.schoolerp.service.SubjectsService;

@RestController
@RequestMapping("/schoolerp/subjects")
@CrossOrigin("*")
public class SubjectsController {

	@Autowired
	private SubjectsService subjectsService;

	// Add Subject
	@PostMapping("/add")
	public Subjects addSubject(@RequestBody SubjectsDTO subjectDTO) throws SubjectsException {

		return subjectsService.addSubject(subjectDTO);
	}

	// Get Subject By Id
	@GetMapping("/{subjectId}")
	public Subjects getSubjectById(@PathVariable Long subjectId) throws SubjectsException {

		return subjectsService.getSubjectById(subjectId);
	}

	// Get All Subjects
	@GetMapping("/all")
	public List<Subjects> getAllSubjects() throws SubjectsException {

		return subjectsService.getAllSubjects();
	}

	// Get Subject By Subject Code
	@GetMapping("/code/{subjectCode}")
	public Subjects getSubjectByCode(@PathVariable String subjectCode) throws SubjectsException {

		return subjectsService.getSubjectByCode(subjectCode);
	}

	// Get Subjects By Class Id
	@GetMapping("/class/{classId}")
	public List<Subjects> getSubjectsByClassId(@PathVariable Long classId) throws SubjectsException {

		return subjectsService.getSubjectsByClassId(classId);
	}

	// Get Subjects By Teacher Id
	@GetMapping("/teacher/{teacherId}")
	public List<Subjects> getSubjectsByTeacherId(@PathVariable Long teacherId) throws SubjectsException {

		return subjectsService.getSubjectsByTeacherId(teacherId);
	}

	// Get Elective Subjects
	@GetMapping("/elective")
	public List<Subjects> getElectiveSubjects() throws SubjectsException {

		return subjectsService.getElectiveSubjects();
	}

	// Get Core Subjects
	@GetMapping("/core")
	public List<Subjects> getCoreSubjects() throws SubjectsException {

		return subjectsService.getCoreSubjects();
	}

	// Update Subject
	@PutMapping("/update/{subjectId}")
	public Subjects updateSubject(@PathVariable Long subjectId, @RequestBody SubjectsDTO subjectDTO)
			throws SubjectsException {

		return subjectsService.updateSubject(subjectId, subjectDTO);
	}

	// Delete Subject
	@DeleteMapping("/delete/{subjectId}")
	public String deleteSubject(@PathVariable Long subjectId) throws SubjectsException {

		return subjectsService.deleteSubject(subjectId);
	}
}