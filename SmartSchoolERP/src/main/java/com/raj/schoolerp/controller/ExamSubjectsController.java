package com.raj.schoolerp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.raj.schoolerp.DTO.ExamSubjectsDTO;
import com.raj.schoolerp.exception.ExamSubjectsException;
import com.raj.schoolerp.model.ExamSubjects;
import com.raj.schoolerp.service.ExamSubjectsService;

@RestController
@RequestMapping("/schoolerp/exam-subjects")
@CrossOrigin("*")
public class ExamSubjectsController {

	@Autowired
	private ExamSubjectsService examSubjectsService;

	@PostMapping("/add")
	public ExamSubjects addExamSubject(@RequestBody ExamSubjectsDTO examSubjectsDTO) throws ExamSubjectsException {

		return examSubjectsService.addExamSubject(examSubjectsDTO);
	}

	@PutMapping("/update/{examSubId}")
	public ExamSubjects updateExamSubject(@PathVariable Long examSubId, @RequestBody ExamSubjectsDTO examSubjectsDTO)
			throws ExamSubjectsException {

		return examSubjectsService.updateExamSubject(examSubId, examSubjectsDTO);
	}

	@DeleteMapping("/delete/{examSubId}")
	public String deleteExamSubject(@PathVariable Long examSubId) throws ExamSubjectsException {

		return examSubjectsService.deleteExamSubject(examSubId);
	}

	@GetMapping("/{examSubId}")
	public ExamSubjects getExamSubjectById(@PathVariable Long examSubId) throws ExamSubjectsException {

		return examSubjectsService.getExamSubjectById(examSubId);
	}

	@GetMapping("/all")
	public List<ExamSubjects> getAllExamSubjects() throws ExamSubjectsException {

		return examSubjectsService.getAllExamSubjects();
	}

	@GetMapping("/exam/{examId}")
	public List<ExamSubjects> getExamSubjectsByExamId(@PathVariable Long examId) throws ExamSubjectsException {

		return examSubjectsService.getExamSubjectsByExamId(examId);
	}

	@GetMapping("/subject/{subjectId}")
	public List<ExamSubjects> getExamSubjectsBySubjectId(@PathVariable Long subjectId) throws ExamSubjectsException {

		return examSubjectsService.getExamSubjectsBySubjectId(subjectId);
	}

	@GetMapping("/date")
	public List<ExamSubjects> getExamSubjectsByDate(@RequestParam LocalDate examDate) throws ExamSubjectsException {

		return examSubjectsService.getExamSubjectsByDate(examDate);
	}

	@GetMapping("/schedule/{examId}")
	public List<ExamSubjects> getExamSchedule(@PathVariable Long examId) throws ExamSubjectsException {

		return examSubjectsService.getExamSchedule(examId);
	}
}