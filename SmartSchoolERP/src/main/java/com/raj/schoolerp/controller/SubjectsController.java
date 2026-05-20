package com.raj.schoolerp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raj.schoolerp.DTO.SubjectsDTO;
import com.raj.schoolerp.exception.SubjectsException;
import com.raj.schoolerp.model.Subjects;
import com.raj.schoolerp.service.SubjectsService;

@RestController
@RequestMapping("/schoolerp/subjects")
@CrossOrigin(origins = "http://localhost:5173")
public class SubjectsController {

	@Autowired
	private SubjectsService subjectsService;

	@PostMapping("/add")
	public Subjects addSubject(@RequestBody SubjectsDTO subjectDTO) throws SubjectsException {

		return subjectsService.addSubject(subjectDTO);
	}

	@GetMapping("/{subjectId}")
	public Subjects getSubjectById(@PathVariable Long subjectId) throws SubjectsException {

		return subjectsService.getSubjectById(subjectId);
	}

	@GetMapping("/all")
	public List<Subjects> getAllSubjects() throws SubjectsException {

		return subjectsService.getAllSubjects();
	}

	@GetMapping("/code/{subjectCode}")
	public Subjects getSubjectByCode(@PathVariable String subjectCode) throws SubjectsException {

		return subjectsService.getSubjectByCode(subjectCode);
	}

	@GetMapping("/class/{classId}")
	public List<Subjects> getSubjectsByClassId(@PathVariable Long classId) throws SubjectsException {

		return subjectsService.getSubjectsByClassId(classId);
	}

	@GetMapping("/teacher/{teacherId}")
	public List<Subjects> getSubjectsByTeacherId(@PathVariable Long teacherId) throws SubjectsException {

		return subjectsService.getSubjectsByTeacherId(teacherId);
	}

	@GetMapping("/elective")
	public List<Subjects> getElectiveSubjects() throws SubjectsException {

		return subjectsService.getElectiveSubjects();
	}

	@GetMapping("/core")
	public List<Subjects> getCoreSubjects() throws SubjectsException {

		return subjectsService.getCoreSubjects();
	}

	@PutMapping("/update/{subjectId}")
	public Subjects updateSubject(@PathVariable Long subjectId, @RequestBody SubjectsDTO subjectDTO)
			throws SubjectsException {

		return subjectsService.updateSubject(subjectId, subjectDTO);
	}

	@GetMapping("/my-subjects")
	public List<Subjects> getMySubjects(Authentication authentication) throws SubjectsException {

		return subjectsService.getMySubjects(authentication.getName());
	}

	@DeleteMapping("/delete/{subjectId}")
	public String deleteSubject(@PathVariable Long subjectId) throws SubjectsException {

		return subjectsService.deleteSubject(subjectId);
	}

}