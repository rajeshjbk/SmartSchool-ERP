package com.raj.schoolerp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.entity.Notices;

@Repository
public interface NoticesRepository extends JpaRepository<Notices, Long> {

}
