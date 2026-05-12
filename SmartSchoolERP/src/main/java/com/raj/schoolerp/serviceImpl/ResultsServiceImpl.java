package com.raj.schoolerp.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.ResultsDTO;
import com.raj.schoolerp.exception.ResultsException;
import com.raj.schoolerp.model.Results;
import com.raj.schoolerp.repository.ResultsRepository;
import com.raj.schoolerp.service.ResultsService;

@Service
public class ResultsServiceImpl implements ResultsService {

	@Autowired
	private ResultsRepository resultsRepo;

	@Override
	public Results addResult(ResultsDTO resultsDTO) throws ResultsException {

		Results newResult = new Results();

		BeanUtils.copyProperties(resultsDTO, newResult);

		return resultsRepo.save(newResult);
	}

	@Override
	public Results updateResult(Long resultId, ResultsDTO resultsDTO) throws ResultsException {

		Results existResult = resultsRepo.findById(resultId)
				.orElseThrow(() -> new ResultsException("Result Not Found"));

		BeanUtils.copyProperties(resultsDTO, existResult);

		return resultsRepo.save(existResult);
	}

	@Override
	public String deleteResult(Long resultId) throws ResultsException {

		resultsRepo.findById(resultId).orElseThrow(() -> new ResultsException("Result Not Found"));

		resultsRepo.deleteById(resultId);

		return "Result deleted with ID: " + resultId;
	}

	@Override
	public Results getResultById(Long resultId) throws ResultsException {

		return resultsRepo.findById(resultId).orElseThrow(() -> new ResultsException("Wrong Result Id"));
	}

	@Override
	public List<Results> getAllResults() throws ResultsException {

		return resultsRepo.findAll();
	}

	@Override
	public List<Results> getResultsByStudentId(Long studentId) throws ResultsException {

		List<Results> results = resultsRepo.findResultsByStudentId(studentId);

		if (results.isEmpty()) {

			throw new ResultsException("No Results Found For This Student");
		}

		return results;
	}

	@Override
	public List<Results> getResultsByExamSubjectId(Long examSubjectId) throws ResultsException {

		List<Results> results = resultsRepo.findResultsByExamSubjectId(examSubjectId);

		if (results.isEmpty()) {

			throw new ResultsException("No Results Found");
		}

		return results;
	}

	@Override
	public List<Results> getStudentResults(Long studentId) throws ResultsException {

		List<Results> results = resultsRepo.findStudentResults(studentId);

		if (results.isEmpty()) {

			throw new ResultsException("Student Results Not Found");
		}

		return results;
	}

	@Override
	public List<Results> getRankList() throws ResultsException {

		List<Results> results = resultsRepo.findRankList();

		if (results.isEmpty()) {

			throw new ResultsException("Rank List Not Found");
		}

		return results;
	}
}