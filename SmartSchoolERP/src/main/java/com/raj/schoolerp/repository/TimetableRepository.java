package com.raj.schoolerp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.raj.schoolerp.entity.Timetable;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {

}
