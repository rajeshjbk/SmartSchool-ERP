package com.raj.schoolerp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.entity.FeeStructures;

@Repository
public interface FeeStructuresRepository extends JpaRepository<FeeStructures, Long> {

}
