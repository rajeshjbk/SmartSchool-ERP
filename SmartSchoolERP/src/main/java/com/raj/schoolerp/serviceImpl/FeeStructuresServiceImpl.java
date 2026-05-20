package com.raj.schoolerp.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.FeeStructuresDTO;
import com.raj.schoolerp.exception.FeeStructuresException;
import com.raj.schoolerp.model.Classes;
import com.raj.schoolerp.model.FeeStructures;
import com.raj.schoolerp.model.Frequency;
import com.raj.schoolerp.model.Students;
import com.raj.schoolerp.repository.ClassesRepository;
import com.raj.schoolerp.repository.FeeStructuresRepository;
import com.raj.schoolerp.repository.StudentsRepository;
import com.raj.schoolerp.service.FeeStructuresService;

@Service
public class FeeStructuresServiceImpl implements FeeStructuresService {

	@Autowired
	private FeeStructuresRepository feeStructuresRepo;

	@Autowired
	private ClassesRepository classesRepository;

	@Autowired
	private StudentsRepository studentsRepo;

	@Override
	public FeeStructures addFeeStructure(FeeStructuresDTO dto) throws FeeStructuresException {

		FeeStructures fee = new FeeStructures();

		BeanUtils.copyProperties(dto, fee);

		Classes classes = classesRepository.findById(dto.getClassId())
				.orElseThrow(() -> new FeeStructuresException("Class Not Found"));

		fee.setClasses(classes);

		return feeStructuresRepo.save(fee);
	}

	@Override
	public FeeStructures updateFeeStructure(Long feeId, FeeStructuresDTO dto) throws FeeStructuresException {

		FeeStructures existFee = feeStructuresRepo.findById(feeId)
				.orElseThrow(() -> new FeeStructuresException("Fee Structure Not Found"));

		BeanUtils.copyProperties(dto, existFee);

		Classes classes = classesRepository.findById(dto.getClassId())
				.orElseThrow(() -> new FeeStructuresException("Class Not Found"));

		existFee.setClasses(classes);

		return feeStructuresRepo.save(existFee);
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

		List<FeeStructures> feeStructures = feeStructuresRepo.findByClasses_ClassId(classId);

		if (feeStructures.isEmpty()) {

			throw new FeeStructuresException("No Fee Structures Found");
		}

		return feeStructures;
	}

	@Override
	public List<FeeStructures> getFeeStructuresByFrequency(Frequency frequency) throws FeeStructuresException {

		List<FeeStructures> feeStructures = feeStructuresRepo.findByFrequency(frequency);

		if (feeStructures.isEmpty()) {

			throw new FeeStructuresException("No Fee Structures Found");
		}

		return feeStructures;
	}

	@Override
	public FeeStructures getFeeByStudentId(Long studentId) throws FeeStructuresException {

		Students student = studentsRepo.findById(studentId)
				.orElseThrow(() -> new FeeStructuresException("Student Not Found"));

		Classes studentClass = student.getClasses();

		return feeStructuresRepo.findFirstByClasses_ClassId(studentClass.getClassId())
				.orElseThrow(() -> new FeeStructuresException("Fee Structure Not Found"));
	}

	@Override
	public void deleteFeeStructure(Long feeStructId) throws FeeStructuresException {

		FeeStructures feeStructure = feeStructuresRepo.findById(feeStructId)
				.orElseThrow(() -> new FeeStructuresException("Fee Structure not found with ID: " + feeStructId));

		feeStructuresRepo.delete(feeStructure);
	}
}