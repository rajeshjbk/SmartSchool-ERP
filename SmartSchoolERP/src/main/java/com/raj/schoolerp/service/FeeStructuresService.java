package com.raj.schoolerp.service;

import java.util.List;

import com.raj.schoolerp.DTO.FeeStructuresDTO;
import com.raj.schoolerp.exception.FeeStructuresException;
import com.raj.schoolerp.model.FeeStructures;
import com.raj.schoolerp.model.Frequency;

public interface FeeStructuresService {

	// Add Fee Structure
	FeeStructures addFeeStructure(FeeStructuresDTO feeStructuresDTO) throws FeeStructuresException;

	// Update Fee Structure
	FeeStructures updateFeeStructure(Long feeStructId, FeeStructuresDTO feeStructuresDTO) throws FeeStructuresException;

	// Get Fee Structure By Id
	FeeStructures getFeeStructureById(Long feeStructId) throws FeeStructuresException;

	// Get All Fee Structures
	List<FeeStructures> getAllFeeStructures() throws FeeStructuresException;

	// Get Fee Structures By Class Id
	List<FeeStructures> getFeeStructuresByClassId(Long classId) throws FeeStructuresException;

	// Get Fee Structures By Frequency
	List<FeeStructures> getFeeStructuresByFrequency(Frequency frequency) throws FeeStructuresException;

	public FeeStructures getFeeByStudentId(Long studentId) throws FeeStructuresException;

	void deleteFeeStructure(Long feeStructId) throws FeeStructuresException;
}