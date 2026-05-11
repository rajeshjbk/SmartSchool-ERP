package com.raj.schoolerp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.entity.ExamSubjects;

@Repository
public interface ExamSubjectsRepository extends JpaRepository<ExamSubjects, Long> {

	// Get Exam Subjects By Exam Id
	@Query("SELECT es FROM ExamSubjects es " + "WHERE es.exam.examId = :examId")
	List<ExamSubjects> findExamSubjectsByExamId(@Param("examId") Long examId);

	// Get Exam Subjects By Subject Id
	@Query("SELECT es FROM ExamSubjects es " + "WHERE es.subjects.subjectId = :subjectId")
	List<ExamSubjects> findExamSubjectsBySubjectId(@Param("subjectId") Long subjectId);

	// Get Exam Subjects By Exam Date
	@Query("SELECT es FROM ExamSubjects es " + "WHERE es.examDate = :examDate")
	List<ExamSubjects> findExamSubjectsByDate(@Param("examDate") LocalDate examDate);

	// Get Exam Schedule By Exam Id
	@Query("SELECT es FROM ExamSubjects es " + "WHERE es.exam.examId = :examId "
			+ "ORDER BY es.examDate ASC, es.startTime ASC")
	List<ExamSubjects> findExamSchedule(@Param("examId") Long examId);
}
