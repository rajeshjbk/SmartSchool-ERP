package com.raj.schoolerp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.model.SubjectType;
import com.raj.schoolerp.model.Subjects;

@Repository
public interface SubjectsRepository extends JpaRepository<Subjects, Long> {

	// Get Subject By Subject Code
	@Query("SELECT s FROM Subjects s WHERE s.subjectCode = :subjectCode")
	Optional<Subjects> findBySubjectCode(@Param("subjectCode") String subjectCode);

	// Get Subjects By Class Id
	@Query("SELECT s FROM Subjects s WHERE s.classes.classId = :classId")
	List<Subjects> findSubjectsByClassId(@Param("classId") Long classId);

	// Get Subjects By Teacher Id
	@Query("SELECT s FROM Subjects s WHERE s.teacher.teacherId = :teacherId")
	List<Subjects> findSubjectsByTeacherId(@Param("teacherId") Long teacherId);

	// Get Elective Subjects
	@Query("SELECT s FROM Subjects s WHERE s.isElective = true")
	List<Subjects> findElectiveSubjects();

	// Get Core Subjects
	@Query("SELECT s FROM Subjects s WHERE s.isElective = false")
	List<Subjects> findCoreSubjects();

	// Get Subjects By Subject Type
	@Query("SELECT s FROM Subjects s WHERE s.subjectType = :subjectType")
	List<Subjects> findSubjectsByType(@Param("subjectType") SubjectType subjectType);

	// Teacher Dashboard
	@Query("""
			SELECT s
			FROM Subjects s
			WHERE s.teacher.teacherId = :teacherId
			""")
	List<Subjects> getTeacherSubjects(@Param("teacherId") Long teacherId);

	// Student Dashboard
	@Query("""
			SELECT sub
			FROM Subjects sub
			JOIN sub.classes c
			JOIN Students s
			ON s.classes.classId = c.classId
			WHERE s.user.userId = :userId
			""")
	List<Subjects> getStudentSubjects(@Param("userId") Long userId);

	boolean existsBySubjectCode(String subjectCode);

}
