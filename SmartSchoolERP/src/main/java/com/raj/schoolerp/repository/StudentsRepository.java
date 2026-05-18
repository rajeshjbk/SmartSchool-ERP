package com.raj.schoolerp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.model.Students;
import com.raj.schoolerp.model.UserRole;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {

	public Optional<Students> findByAdmissionNo(String admissionId);

	boolean existsByUser_UserId(Long userId);

	@Query("""
			SELECT s
			FROM Students s
			WHERE s.user.role
			= :role
			""")
	List<Students> getStudentByRole(@Param("role") UserRole role);

	// Parent → Child Profile
	@Query("""
			SELECT s
			FROM Students s
			WHERE s.parent.userId = :parentId
			""")
	Optional<Students> getStudentByParentId(@Param("parentId") Long parentId);

	// Teacher → Students by Class
	@Query("""
			SELECT s
			FROM Students s
			WHERE s.classes.classId = :classId
			""")
	List<Students> getStudentsByClass(@Param("classId") Long classId);

	// Student Dashboard
	@Query("""
			SELECT s
			FROM Students s
			WHERE s.user.userId = :userId
			""")
	Optional<Students> getStudentByUserId(@Param("userId") Long userId);

}
