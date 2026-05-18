package com.raj.schoolerp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.model.DayOfWeek;
import com.raj.schoolerp.model.Timetable;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {

	// Get Timetable By Class Id
	@Query("SELECT t FROM Timetable t " + "JOIN t.classes c " + "WHERE c.classId = :classId")
	List<Timetable> findTimetableByClassId(@Param("classId") Long classId);

	// Get Timetable By Teacher Id
	@Query("SELECT t FROM Timetable t " + "WHERE t.teachers.teacherId = :teacherId")
	List<Timetable> findTimetableByTeacherId(@Param("teacherId") Long teacherId);

	// Get Timetable By Subject Id
	@Query("SELECT t FROM Timetable t " + "WHERE t.subject.subjectId = :subjectId")
	List<Timetable> findTimetableBySubjectId(@Param("subjectId") Long subjectId);

	// Get Timetable By Day
	@Query("SELECT t FROM Timetable t " + "WHERE t.dayOfWeek = :dayOfWeek")
	List<Timetable> findTimetableByDay(@Param("dayOfWeek") DayOfWeek dayOfWeek);

	// Get Class Timetable By Day
	@Query("SELECT t FROM Timetable t " + "JOIN t.classes c " + "WHERE c.classId = :classId "
			+ "AND t.dayOfWeek = :dayOfWeek " + "ORDER BY t.periodOfTime ASC")
	List<Timetable> findClassTimetableByDay(@Param("classId") Long classId, @Param("dayOfWeek") DayOfWeek dayOfWeek);

	

		// Teacher Dashboard
		@Query("""
				SELECT t
				FROM Timetable t
				WHERE t.teachers.teacherId = :teacherId
				ORDER BY t.dayOfWeek,
				         t.periodOfTime
				""")
		List<Timetable> getTeacherTimetable(@Param("teacherId") Long teacherId);

		// Student Dashboard
		@Query("""
				SELECT t
				FROM Timetable t
				JOIN t.classes c
				JOIN Students s
				ON s.classes.classId = c.classId
				WHERE s.user.userId = :userId
				""")
		List<Timetable> getStudentTimetable(@Param("userId") Long userId);

		// Parent Dashboard
		@Query("""
				SELECT t
				FROM Timetable t
				JOIN t.classes c
				JOIN Students s
				ON s.classes.classId = c.classId
				WHERE s.parent.userId = :parentId
				""")
		List<Timetable> getParentTimetable(@Param("parentId") Long parentId);
	
}
