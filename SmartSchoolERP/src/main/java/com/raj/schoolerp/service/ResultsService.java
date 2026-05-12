package com.raj.schoolerp.service;

import java.util.List;

import com.raj.schoolerp.DTO.ResultsDTO;
import com.raj.schoolerp.exception.ResultsException;
import com.raj.schoolerp.model.Results;

public interface ResultsService {

	// Add Result
	Results addResult(ResultsDTO resultsDTO) throws ResultsException;

	// Update Result
	Results updateResult(Long resultId, ResultsDTO resultsDTO) throws ResultsException;

	// Delete Result
	String deleteResult(Long resultId) throws ResultsException;

	// Get Result By Id
	Results getResultById(Long resultId) throws ResultsException;

	// Get All Results
	List<Results> getAllResults() throws ResultsException;

	// Get Results By Student Id
	List<Results> getResultsByStudentId(Long studentId) throws ResultsException;

	// Get Results By Exam Subject Id
	List<Results> getResultsByExamSubjectId(Long examSubjectId) throws ResultsException;

	// Get Student Results
	List<Results> getStudentResults(Long studentId) throws ResultsException;

	// Get Class Rank List
	List<Results> getRankList() throws ResultsException;
}