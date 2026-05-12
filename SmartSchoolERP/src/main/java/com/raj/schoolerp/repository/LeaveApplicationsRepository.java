package com.raj.schoolerp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.model.LeaveApplications;

@Repository
public interface LeaveApplicationsRepository extends JpaRepository<LeaveApplications, Long> {

}
