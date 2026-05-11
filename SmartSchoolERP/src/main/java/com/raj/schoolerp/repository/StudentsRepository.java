package com.raj.schoolerp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.entity.Students;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {

	public Optional<Students> findByAdmissionNo(String admissionId);
}
