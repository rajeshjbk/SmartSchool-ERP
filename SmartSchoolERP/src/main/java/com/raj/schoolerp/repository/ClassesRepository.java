package com.raj.schoolerp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.model.Classes;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {

	  // Find class by class name
    List<Classes> findByClassNameIgnoreCase(
            String className);

    // Find class by section
    List<Classes> findBySectionIgnoreCase(
            String section);

    // Find class by academic year
    List<Classes> findByAcademicYear(
            String academicYear);

    // Find class by room number
    Optional<Classes> findByRoomNo(String roomNo);

    // Find class by teacher id
    Optional<Classes> findByTeacherTeacherId(
            Long teacherId);

    // Check duplicate class + section
    boolean existsByClassNameAndSection(
            String className,
            String section);
}
