package com.raj.schoolerp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.raj.schoolerp.DTO.ExamsDTO;
import com.raj.schoolerp.entity.ExamStatus;
import com.raj.schoolerp.entity.Exams;
import com.raj.schoolerp.exception.ExamsException;
import com.raj.schoolerp.service.ExamsService;

@RestController
@RequestMapping("/exams")
@CrossOrigin("*")
public class ExamsController {

	@Autowired
	private ExamsService examsService;

	// Add Exam
	@PostMapping("/add")
	public Exams addExam(@RequestBody ExamsDTO examsDTO) throws ExamsException {

		return examsService.addExam(examsDTO);
	}

	// Update Exam
	@PutMapping("/update/{examId}")
	public Exams updateExam(@PathVariable Long examId, @RequestBody ExamsDTO examsDTO) throws ExamsException {

		return examsService.updateExam(examId, examsDTO);
	}

	// Delete Exam
	@DeleteMapping("/delete/{examId}")
	public String deleteExam(@PathVariable Long examId) throws ExamsException {

		return examsService.deleteExam(examId);
	}

	// Get Exam By Id
	@GetMapping("/{examId}")
	public Exams getExamById(@PathVariable Long examId) throws ExamsException {

		return examsService.getExamById(examId);
	}

	// Get All Exams
	@GetMapping("/all")
	public List<Exams> getAllExams() throws ExamsException {

		return examsService.getAllExams();
	}

	// Get Exams By Class Id
	@GetMapping("/class/{classId}")
	public List<Exams> getExamsByClassId(@PathVariable Long classId) throws ExamsException {

		return examsService.getExamsByClassId(classId);
	}

	// Get Exams By Academic Year
	@GetMapping("/academic-year/{academicYear}")
	public List<Exams> getExamsByAcademicYear(@PathVariable String academicYear) throws ExamsException {

		return examsService.getExamsByAcademicYear(academicYear);
	}

	// Get Exams By Status
	@GetMapping("/status/{examStatus}")
	public List<Exams> getExamsByStatus(@PathVariable ExamStatus examStatus) throws ExamsException {

		return examsService.getExamsByStatus(examStatus);
	}

	// Get Exams Between Dates
	@GetMapping("/between-dates")
	public List<Exams> getExamsBetweenDates(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate)
			throws ExamsException {

		return examsService.getExamsBetweenDates(startDate, endDate);
	}

	// Get Upcoming Exams
	@GetMapping("/upcoming")
	public List<Exams> getUpcomingExams() throws ExamsException {

		return examsService.getUpcomingExams();
	}
}