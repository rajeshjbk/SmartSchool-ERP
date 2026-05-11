package com.raj.schoolerp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.entity.ExamSubjects;

@Repository
public interface ExamSubjectsRepository extends JpaRepository<ExamSubjects, Long> {

}
