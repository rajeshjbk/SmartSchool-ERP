package com.raj.schoolerp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.raj.schoolerp.DTO.ResultsDTO;
import com.raj.schoolerp.exception.ResultsException;
import com.raj.schoolerp.model.Results;
import com.raj.schoolerp.service.ResultsService;

@RestController
@RequestMapping("/results")
@CrossOrigin("*")
public class ResultsController {

	@Autowired
	private ResultsService resultsService;

	@PostMapping("/add")
	public Results addResult(@RequestBody ResultsDTO resultsDTO) throws ResultsException {

		return resultsService.addResult(resultsDTO);
	}

	@PutMapping("/update/{resultId}")
	public Results updateResult(@PathVariable Long resultId, @RequestBody ResultsDTO resultsDTO)
			throws ResultsException {

		return resultsService.updateResult(resultId, resultsDTO);
	}

	@DeleteMapping("/delete/{resultId}")
	public String deleteResult(@PathVariable Long resultId) throws ResultsException {

		return resultsService.deleteResult(resultId);
	}

	@GetMapping("/{resultId}")
	public Results getResultById(@PathVariable Long resultId) throws ResultsException {

		return resultsService.getResultById(resultId);
	}

	@GetMapping("/all")
	public List<Results> getAllResults() throws ResultsException {

		return resultsService.getAllResults();
	}

	@GetMapping("/student/{studentId}")
	public List<Results> getResultsByStudentId(@PathVariable Long studentId) throws ResultsException {

		return resultsService.getResultsByStudentId(studentId);
	}

	@GetMapping("/exam-subject/{examSubjectId}")
	public List<Results> getResultsByExamSubjectId(@PathVariable Long examSubjectId) throws ResultsException {

		return resultsService.getResultsByExamSubjectId(examSubjectId);
	}

	@GetMapping("/student-results/{studentId}")
	public List<Results> getStudentResults(@PathVariable Long studentId) throws ResultsException {

		return resultsService.getStudentResults(studentId);
	}

	@GetMapping("/rank-list")
	public List<Results> getRankList() throws ResultsException {

		return resultsService.getRankList();
	}
}