package com.raj.schoolerp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.model.ExamStatus;
import com.raj.schoolerp.model.Exams;

@Repository
public interface ExamsRepository extends JpaRepository<Exams, Long> {

	// Get Exams By Class Id
	@Query("SELECT e FROM Exams e " + "WHERE e.classes.classId = :classId")
	List<Exams> findExamsByClassId(@Param("classId") Long classId);

	// Get Exams By Academic Year
	@Query("SELECT e FROM Exams e " + "WHERE e.academicYear = :academicYear")
	List<Exams> findExamsByAcademicYear(@Param("academicYear") String academicYear);

	// Get Exams By Status
	@Query("SELECT e FROM Exams e " + "WHERE e.examStatus = :examStatus")
	List<Exams> findExamsByStatus(@Param("examStatus") ExamStatus examStatus);

	// Get Exams Between Dates
	@Query("SELECT e FROM Exams e " + "WHERE e.startDate >= :startDate " + "AND e.endDate <= :endDate")
	List<Exams> findExamsBetweenDates(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);

	// Get Upcoming Exams
	@Query("SELECT e FROM Exams e " + "WHERE e.startDate > CURRENT_DATE")
	List<Exams> findUpcomingExams();
}
