package com.raj.schoolerp.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.ExamsDTO;
import com.raj.schoolerp.entity.ExamStatus;
import com.raj.schoolerp.entity.Exams;
import com.raj.schoolerp.exception.ExamsException;
import com.raj.schoolerp.repository.ExamsRepository;
import com.raj.schoolerp.service.ExamsService;

@Service
public class ExamsServiceImpl implements ExamsService {

	@Autowired
	private ExamsRepository examsRepo;

	// Add Exam
	@Override
	public Exams addExam(ExamsDTO examsDTO) throws ExamsException {

		Exams newExam = new Exams();

		BeanUtils.copyProperties(examsDTO, newExam);

		return examsRepo.save(newExam);
	}

	// Update Exam
	@Override
	public Exams updateExam(Long examId, ExamsDTO examsDTO) throws ExamsException {

		Exams existExam = examsRepo.findById(examId).orElseThrow(() -> new ExamsException("Exam Not Found"));

		BeanUtils.copyProperties(examsDTO, existExam);

		return examsRepo.save(existExam);
	}

	// Delete Exam
	@Override
	public String deleteExam(Long examId) throws ExamsException {

		examsRepo.findById(examId).orElseThrow(() -> new ExamsException("Exam Not Found"));

		examsRepo.deleteById(examId);

		return "Exam deleted with Exam ID: " + examId;
	}

	// Get Exam By Id
	@Override
	public Exams getExamById(Long examId) throws ExamsException {

		return examsRepo.findById(examId).orElseThrow(() -> new ExamsException("Wrong Exam Id"));
	}

	// Get All Exams
	@Override
	public List<Exams> getAllExams() throws ExamsException {

		return examsRepo.findAll();
	}

	// Get Exams By Class Id
	@Override
	public List<Exams> getExamsByClassId(Long classId) throws ExamsException {

		List<Exams> exams = examsRepo.findExamsByClassId(classId);

		if (exams.isEmpty()) {

			throw new ExamsException("No Exams Found For This Class");
		}

		return exams;
	}

	// Get Exams By Academic Year
	@Override
	public List<Exams> getExamsByAcademicYear(String academicYear) throws ExamsException {

		List<Exams> exams = examsRepo.findExamsByAcademicYear(academicYear);

		if (exams.isEmpty()) {

			throw new ExamsException("No Exams Found For Academic Year: " + academicYear);
		}

		return exams;
	}

	// Get Exams By Status
	@Override
	public List<Exams> getExamsByStatus(ExamStatus examStatus) throws ExamsException {

		List<Exams> exams = examsRepo.findExamsByStatus(examStatus);

		if (exams.isEmpty()) {

			throw new ExamsException("No Exams Found With Status: " + examStatus);
		}

		return exams;
	}

	// Get Exams Between Dates
	@Override
	public List<Exams> getExamsBetweenDates(LocalDate startDate, LocalDate endDate) throws ExamsException {

		List<Exams> exams = examsRepo.findExamsBetweenDates(startDate, endDate);

		if (exams.isEmpty()) {

			throw new ExamsException("No Exams Found Between Dates");
		}

		return exams;
	}

	// Get Upcoming Exams
	@Override
	public List<Exams> getUpcomingExams() throws ExamsException {

		List<Exams> exams = examsRepo.findUpcomingExams();

		if (exams.isEmpty()) {

			throw new ExamsException("No Upcoming Exams Found");
		}

		return exams;
	}
}