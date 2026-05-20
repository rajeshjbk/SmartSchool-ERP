package com.raj.schoolerp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.model.Results;

@Repository
public interface ResultsRepository extends JpaRepository<Results, Long> {

	// Get Results By Student Id
	@Query("SELECT r FROM Results r " + "WHERE r.student.studentId = :studentId")
	List<Results> findResultsByStudentId(@Param("studentId") Long studentId);

	// Get Results By Exam Subject Id
	@Query("SELECT r FROM Results r " + "WHERE r.examSubjects.examSubId = :examSubjectId")
	List<Results> findResultsByExamSubjectId(@Param("examSubjectId") Long examSubjectId);

	// Get Student Results
	@Query("SELECT r FROM Results r " + "WHERE r.student.studentId = :studentId")
	List<Results> findStudentResults(@Param("studentId") Long studentId);

	// Get Class Rank List
	@Query("SELECT r FROM Results r " + "ORDER BY r.rankInClass ASC")
	List<Results> findRankList();

	// Student Dashboard
	@Query("""
			SELECT r
			FROM Results r
			WHERE r.student.user.userId = :userId
			""")
	List<Results> getStudentResults(@Param("userId") Long userId);

	// Parent Dashboard
	@Query("""
	        SELECT r
	        FROM Results r
	        JOIN FETCH r.student s
	        WHERE s.parent.userId = :parentId
	        """)
	List<Results> getParentResults(@Param("parentId") Long parentId);

	// Teacher Dashboard
	@Query("""
			SELECT r
			FROM Results r
			WHERE r.examSubjects.subjects.teacher.teacherId = :teacherId
			""")
	List<Results> getTeacherResults(@Param("teacherId") Long teacherId);

	// Result by Exam
	@Query("""
			SELECT r
			FROM Results r
			WHERE r.examSubjects.exam.examId = :examId
			""")
	List<Results> getResultsByExam(@Param("examId") Long examId);
}
