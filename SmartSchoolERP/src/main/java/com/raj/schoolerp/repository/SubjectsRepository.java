package com.raj.schoolerp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.raj.schoolerp.entity.Subjects;

@Repository
public interface SubjectsRepository extends JpaRepository<Subjects, Long> {

}
