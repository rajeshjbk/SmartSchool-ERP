package com.raj.schoolerp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.model.Audience;
import com.raj.schoolerp.model.Notices;

@Repository
public interface NoticesRepository extends JpaRepository<Notices, Long> {

	// Get Notices By Audience
	@Query("SELECT n FROM Notices n " + "WHERE n.audience = :audience")
	List<Notices> findNoticesByAudience(@Param("audience") Audience audience);

	// Get Active Notices
	@Query("SELECT n FROM Notices n " + "WHERE n.expiryDate >= CURRENT_DATE")
	List<Notices> findActiveNotices();
}
