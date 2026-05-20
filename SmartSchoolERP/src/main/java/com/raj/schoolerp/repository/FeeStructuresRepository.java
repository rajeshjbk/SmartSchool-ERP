package com.raj.schoolerp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.model.FeeStructures;
import com.raj.schoolerp.model.Frequency;

@Repository
public interface FeeStructuresRepository extends JpaRepository<FeeStructures, Long> {

	// Get Fee Structures By Class Id
	@Query("SELECT fs FROM FeeStructures fs " + "WHERE fs.classes.classId = :classId")
	List<FeeStructures> findFeeStructuresByClassId(@Param("classId") Long classId);

	// Get Fee Structures By Frequency
	@Query("SELECT fs FROM FeeStructures fs " + "WHERE fs.frequency = :frequency")
	List<FeeStructures> findFeeStructuresByFrequency(@Param("frequency") Frequency frequency);

	// Get Fee Structures by Class Id
	List<FeeStructures> findByClasses_ClassId(Long classId);

	// Get Fee Structures by Frequency
	List<FeeStructures> findByFrequency(Frequency frequency);

	Optional<FeeStructures> findFirstByClasses_ClassId(Long classId);

	
}
