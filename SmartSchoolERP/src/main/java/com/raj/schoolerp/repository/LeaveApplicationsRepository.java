package com.raj.schoolerp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.model.LeaveApplications;
import com.raj.schoolerp.model.LeaveStatus;

@Repository
public interface LeaveApplicationsRepository extends JpaRepository<LeaveApplications, Long> {

	// Get Leave Applications By User Id
	@Query("SELECT la FROM LeaveApplications la " + "WHERE la.user.userId = :userId")
	List<LeaveApplications> findLeaveApplicationsByUserId(@Param("userId") Long userId);

	// Get Leave Applications By Status
	@Query("SELECT la FROM LeaveApplications la " + "WHERE la.leaveStatus = :leaveStatus")
	List<LeaveApplications> findLeaveApplicationsByStatus(@Param("leaveStatus") LeaveStatus leaveStatus);

	// Student Leave
	@Query("""
			SELECT l
			FROM LeaveApplications l
			WHERE l.user.userId = :userId
			""")
	List<LeaveApplications> getUserLeaves(@Param("userId") Long userId);

	// Teacher Leave
	@Query("""
			SELECT l
			FROM LeaveApplications l
			WHERE l.user.role = 'TEACHER'
			""")
	List<LeaveApplications> getTeacherLeaves();

	// Parent Leave
	@Query("""
			SELECT l
			FROM LeaveApplications l
			WHERE l.user.userId =
			(
			    SELECT s.user.userId
			    FROM Students s
			    WHERE s.parent.userId = :parentId
			)
			""")
	List<LeaveApplications> getParentLeaves(@Param("parentId") Long parentId);
}
