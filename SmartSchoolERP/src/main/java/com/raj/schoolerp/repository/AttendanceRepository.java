package com.raj.schoolerp.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.model.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	// Get Attendance By Student Id
	@Query("SELECT a FROM Attendance a " + "WHERE a.students.studentId = :studentId")
	List<Attendance> findAttendanceByStudentId(@Param("studentId") Long studentId);

	// Get Attendance By Class Id
	@Query("SELECT a FROM Attendance a " + "WHERE a.classes.classId = :classId")
	List<Attendance> findAttendanceByClassId(@Param("classId") Long classId);

	// Get Attendance By Date
	@Query("SELECT a FROM Attendance a " + "WHERE DATE(a.date) = DATE(:date)")
	List<Attendance> findAttendanceByDate(@Param("date") Date date);

	// Get Student Attendance By Date
	@Query("SELECT a FROM Attendance a " + "WHERE a.students.studentId = :studentId "
			+ "AND DATE(a.date) = DATE(:date)")
	Optional<Attendance> findStudentAttendanceByDate(@Param("studentId") Long studentId, @Param("date") Date date);

	// Get Attendance Between Dates
	@Query("SELECT a FROM Attendance a " + "WHERE a.date BETWEEN :startDate AND :endDate")
	List<Attendance> findAttendanceBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	// Student Dashboard
	@Query("""
			SELECT a
			FROM Attendance a
			WHERE a.students.user.userId = :userId
			""")
	List<Attendance> getStudentAttendance(@Param("userId") Long userId);

	// Parent Dashboard
	@Query("""
			SELECT a
			FROM Attendance a
			WHERE a.students.parent.userId = :parentId
			""")
	List<Attendance> getAttendanceByParent(@Param("parentId") Long parentId);

	// Teacher Dashboard
	@Query("""
			SELECT a
			FROM Attendance a
			WHERE a.user.userId = :userId
			""")
	List<Attendance> getAttendanceByTeacher(@Param("userId") Long teacherId);

	// Class-wise Attendance
	@Query("""
			SELECT a
			FROM Attendance a
			WHERE a.classes.classId = :classId
			""")
	List<Attendance> getAttendanceByClass(@Param("classId") Long classId);
}
