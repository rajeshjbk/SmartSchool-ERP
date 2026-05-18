package com.raj.schoolerp.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.ExamSubjectsDTO;
import com.raj.schoolerp.exception.ExamSubjectsException;
import com.raj.schoolerp.model.ExamSubjects;
import com.raj.schoolerp.model.Exams;
import com.raj.schoolerp.model.Subjects;
import com.raj.schoolerp.repository.ExamSubjectsRepository;
import com.raj.schoolerp.repository.ExamsRepository;
import com.raj.schoolerp.repository.SubjectsRepository;
import com.raj.schoolerp.service.ExamSubjectsService;

@Service
public class ExamSubjectsServiceImpl implements ExamSubjectsService {

	@Autowired
	private ExamSubjectsRepository examSubjectsRepo;

	@Autowired
	private SubjectsRepository subjectsRepo;

	@Autowired
	private ExamsRepository examsRepo;

	@Override
	public ExamSubjects addExamSubject(ExamSubjectsDTO dto) throws ExamSubjectsException {

		Exams exam = examsRepo.findById(dto.getExamId()).orElseThrow(() -> new ExamSubjectsException("Exam not found"));

		Subjects subject = subjectsRepo.findById(dto.getSubjectId())
				.orElseThrow(() -> new ExamSubjectsException("Subject not found"));

		ExamSubjects examSubject = new ExamSubjects();

		BeanUtils.copyProperties(dto, examSubject);

		examSubject.setExam(exam);

		examSubject.setSubjects(subject);

		return examSubjectsRepo.save(examSubject);
	}

	@Override
	public ExamSubjects updateExamSubject(Long examSubId, ExamSubjectsDTO examSubjectsDTO)
			throws ExamSubjectsException {

		ExamSubjects existExamSubject = examSubjectsRepo.findById(examSubId)
				.orElseThrow(() -> new ExamSubjectsException("Exam Subject Not Found"));

		// Fetch Exam
		Exams exam = examsRepo.findById(examSubjectsDTO.getExamId())
				.orElseThrow(() -> new ExamSubjectsException("Exam not found"));

		// Fetch Subject
		Subjects subject = subjectsRepo.findById(examSubjectsDTO.getSubjectId())
				.orElseThrow(() -> new ExamSubjectsException("Subject not found"));

		BeanUtils.copyProperties(examSubjectsDTO, existExamSubject);

		// Set Relations
		existExamSubject.setExam(exam);

		existExamSubject.setSubjects(subject);

		return examSubjectsRepo.save(existExamSubject);
	}

	@Override
	public String deleteExamSubject(Long examSubId) throws ExamSubjectsException {

		examSubjectsRepo.findById(examSubId).orElseThrow(() -> new ExamSubjectsException("Exam Subject Not Found"));

		examSubjectsRepo.deleteById(examSubId);

		return "Exam Subject deleted with ID: " + examSubId;
	}

	@Override
	public ExamSubjects getExamSubjectById(Long examSubId) throws ExamSubjectsException {

		return examSubjectsRepo.findById(examSubId)
				.orElseThrow(() -> new ExamSubjectsException("Wrong Exam Subject Id"));
	}

	@Override
	public List<ExamSubjects> getAllExamSubjects() throws ExamSubjectsException {

		return examSubjectsRepo.findAll();
	}

	@Override
	public List<ExamSubjects> getExamSubjectsByExamId(Long examId) throws ExamSubjectsException {

		List<ExamSubjects> examSubjects = examSubjectsRepo.findExamSubjectsByExamId(examId);

		if (examSubjects.isEmpty()) {

			throw new ExamSubjectsException("No Exam Subjects Found");
		}

		return examSubjects;
	}

	@Override
	public List<ExamSubjects> getExamSubjectsBySubjectId(Long subjectId) throws ExamSubjectsException {

		List<ExamSubjects> examSubjects = examSubjectsRepo.findExamSubjectsBySubjectId(subjectId);

		if (examSubjects.isEmpty()) {

			throw new ExamSubjectsException("No Subjects Found");
		}

		return examSubjects;
	}

	@Override
	public List<ExamSubjects> getExamSubjectsByDate(LocalDate examDate) throws ExamSubjectsException {

		List<ExamSubjects> examSubjects = examSubjectsRepo.findExamSubjectsByDate(examDate);

		if (examSubjects.isEmpty()) {

			throw new ExamSubjectsException("No Exams Scheduled On This Date");
		}

		return examSubjects;
	}

	@Override
	public List<ExamSubjects> getExamSchedule(Long examId) throws ExamSubjectsException {

		List<ExamSubjects> examSchedule = examSubjectsRepo.findExamSchedule(examId);

		if (examSchedule.isEmpty()) {

			throw new ExamSubjectsException("Exam Schedule Not Found");
		}

		return examSchedule;
	}
}