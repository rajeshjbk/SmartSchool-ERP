package com.raj.schoolerp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.raj.schoolerp.DTO.FeeStructuresDTO;
import com.raj.schoolerp.exception.FeeStructuresException;
import com.raj.schoolerp.model.FeeStructures;
import com.raj.schoolerp.model.Frequency;
import com.raj.schoolerp.service.FeeStructuresService;

@RestController
@RequestMapping("/fee-structures")
@CrossOrigin("*")
public class FeeStructuresController {

	@Autowired
	private FeeStructuresService feeStructuresService;

	@PostMapping("/add")
	public FeeStructures addFeeStructure(@RequestBody FeeStructuresDTO feeStructuresDTO) throws FeeStructuresException {

		return feeStructuresService.addFeeStructure(feeStructuresDTO);
	}

	@PutMapping("/update/{feeStructId}")
	public FeeStructures updateFeeStructure(@PathVariable Long feeStructId,
			@RequestBody FeeStructuresDTO feeStructuresDTO) throws FeeStructuresException {

		return feeStructuresService.updateFeeStructure(feeStructId, feeStructuresDTO);
	}

	@GetMapping("/{feeStructId}")
	public FeeStructures getFeeStructureById(@PathVariable Long feeStructId) throws FeeStructuresException {

		return feeStructuresService.getFeeStructureById(feeStructId);
	}

	@GetMapping("/all")
	public List<FeeStructures> getAllFeeStructures() throws FeeStructuresException {

		return feeStructuresService.getAllFeeStructures();
	}

	@GetMapping("/class/{classId}")
	public List<FeeStructures> getFeeStructuresByClassId(@PathVariable Long classId) throws FeeStructuresException {

		return feeStructuresService.getFeeStructuresByClassId(classId);
	}

	@GetMapping("/frequency/{frequency}")
	public List<FeeStructures> getFeeStructuresByFrequency(@PathVariable Frequency frequency)
			throws FeeStructuresException {

		return feeStructuresService.getFeeStructuresByFrequency(frequency);
	}
}