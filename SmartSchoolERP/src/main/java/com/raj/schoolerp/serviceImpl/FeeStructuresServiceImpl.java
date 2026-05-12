package com.raj.schoolerp.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.FeeStructuresDTO;
import com.raj.schoolerp.exception.FeeStructuresException;
import com.raj.schoolerp.model.FeeStructures;
import com.raj.schoolerp.model.Frequency;
import com.raj.schoolerp.repository.FeeStructuresRepository;
import com.raj.schoolerp.service.FeeStructuresService;

@Service
public class FeeStructuresServiceImpl implements FeeStructuresService {

	@Autowired
	private FeeStructuresRepository feeStructuresRepo;

	@Override
	public FeeStructures addFeeStructure(FeeStructuresDTO feeStructuresDTO) throws FeeStructuresException {

		FeeStructures newFeeStructure = new FeeStructures();

		BeanUtils.copyProperties(feeStructuresDTO, newFeeStructure);

		return feeStructuresRepo.save(newFeeStructure);
	}

	@Override
	public FeeStructures updateFeeStructure(Long feeStructId, FeeStructuresDTO feeStructuresDTO)
			throws FeeStructuresException {

		FeeStructures existFeeStructure = feeStructuresRepo.findById(feeStructId)
				.orElseThrow(() -> new FeeStructuresException("Fee Structure Not Found"));

		BeanUtils.copyProperties(feeStructuresDTO, existFeeStructure);

		return feeStructuresRepo.save(existFeeStructure);
	}

	@Override
	public FeeStructures getFeeStructureById(Long feeStructId) throws FeeStructuresException {

		return feeStructuresRepo.findById(feeStructId)
				.orElseThrow(() -> new FeeStructuresException("Wrong Fee Structure Id"));
	}

	@Override
	public List<FeeStructures> getAllFeeStructures() throws FeeStructuresException {

		return feeStructuresRepo.findAll();
	}

	@Override
	public List<FeeStructures> getFeeStructuresByClassId(Long classId) throws FeeStructuresException {

		List<FeeStructures> feeStructures = feeStructuresRepo.findFeeStructuresByClassId(classId);

		if (feeStructures.isEmpty()) {

			throw new FeeStructuresException("No Fee Structures Found");
		}

		return feeStructures;
	}

	@Override
	public List<FeeStructures> getFeeStructuresByFrequency(Frequency frequency) throws FeeStructuresException {

		List<FeeStructures> feeStructures = feeStructuresRepo.findFeeStructuresByFrequency(frequency);

		if (feeStructures.isEmpty()) {

			throw new FeeStructuresException("No Fee Structures Found");
		}

		return feeStructures;
	}
}