package com.raj.schoolerp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.entity.Exams;

@Repository
public interface ExamsRepository extends JpaRepository<Exams, Long> {

}
