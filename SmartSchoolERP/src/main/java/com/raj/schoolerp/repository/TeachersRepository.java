package com.raj.schoolerp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.model.TeacherStatus;
import com.raj.schoolerp.model.Teachers;

@Repository
public interface TeachersRepository extends JpaRepository<Teachers, Long> {

	// Find Teacher By Employee Id
	Optional<Teachers> findByEmployeeId(String employeeId);

	// Find Teachers By Department
	List<Teachers> findByDepartmentIgnoreCase(String department);

	// Find Teachers By Designation
	List<Teachers> findByDesignationIgnoreCase(String designation);

	// Find Teachers By Status
	List<Teachers> findByTeacherStatus(TeacherStatus status);

	// Find Teacher By User Id
	Optional<Teachers> findByUserUserId(Long userId);

	// Search Teacher By Name
	List<Teachers> findByUserFullNameContainingIgnoreCase(String fullName);

	// Find Teachers By Qualification
	List<Teachers> findByQualificationIgnoreCase(String qualification);

	// Check Employee Id Exists
	boolean existsByEmployeeId(String employeeId);

	boolean existsByUser_UserId(Long userId);
	
}
