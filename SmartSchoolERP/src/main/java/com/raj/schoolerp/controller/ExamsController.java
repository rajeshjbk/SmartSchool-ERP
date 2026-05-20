package com.raj.schoolerp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raj.schoolerp.DTO.ExamsDTO;
import com.raj.schoolerp.exception.ExamsException;
import com.raj.schoolerp.model.ExamStatus;
import com.raj.schoolerp.model.Exams;
import com.raj.schoolerp.service.ExamsService;

@RestController
@RequestMapping("/schoolerp/exams")
@CrossOrigin(origins = "http://localhost:5173")
public class ExamsController {

	@Autowired
	private ExamsService examsService;

	@PostMapping("/add")
	public Exams addExam(@RequestBody ExamsDTO examsDTO) throws ExamsException {

		return examsService.addExam(examsDTO);
	}

	@PutMapping("/update/{examId}")
	public Exams updateExam(@PathVariable Long examId, @RequestBody ExamsDTO examsDTO) throws ExamsException {

		return examsService.updateExam(examId, examsDTO);
	}

	@DeleteMapping("/delete/{examId}")
	public String deleteExam(@PathVariable Long examId) throws ExamsException {

		return examsService.deleteExam(examId);
	}

	@GetMapping("/{examId}")
	public Exams getExamById(@PathVariable Long examId) throws ExamsException {

		return examsService.getExamById(examId);
	}

	@GetMapping("/all")
	public List<Exams> getAllExams() throws ExamsException {

		return examsService.getAllExams();
	}

	@GetMapping("/class/{classId}")
	public List<Exams> getExamsByClassId(@PathVariable Long classId) throws ExamsException {

		return examsService.getExamsByClassId(classId);
	}

	@GetMapping("/academic-year/{academicYear}")
	public List<Exams> getExamsByAcademicYear(@PathVariable String academicYear) throws ExamsException {

		return examsService.getExamsByAcademicYear(academicYear);
	}

	@GetMapping("/status/{examStatus}")
	public List<Exams> getExamsByStatus(@PathVariable ExamStatus examStatus) throws ExamsException {

		return examsService.getExamsByStatus(examStatus);
	}

	@GetMapping("/between-dates")
	public List<Exams> getExamsBetweenDates(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate)
			throws ExamsException {

		return examsService.getExamsBetweenDates(startDate, endDate);
	}

	@GetMapping("/upcoming")
	public List<Exams> getUpcomingExams() throws ExamsException {

		return examsService.getUpcomingExams();
	}

	@GetMapping("/my-exams")
	public List<Exams> getMyExams(Authentication authentication) throws ExamsException {

		return examsService.getMyExams(authentication.getName());
	}

	@GetMapping("/parent/{parentId}")
	public ResponseEntity<List<Exams>> getParentExams(@PathVariable Long parentId) throws ExamsException {

		return new ResponseEntity<>(examsService.getParentExams(parentId), HttpStatus.OK);
	}
}