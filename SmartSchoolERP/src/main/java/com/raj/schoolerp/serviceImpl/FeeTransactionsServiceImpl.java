package com.raj.schoolerp.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.FeeTransactionsDTO;
import com.raj.schoolerp.exception.FeeTransactionsException;
import com.raj.schoolerp.model.FeeStructures;
import com.raj.schoolerp.model.FeeTransactionStatus;
import com.raj.schoolerp.model.FeeTransactions;
import com.raj.schoolerp.model.Students;
import com.raj.schoolerp.repository.FeeStructuresRepository;
import com.raj.schoolerp.repository.FeeTransactionsRepository;
import com.raj.schoolerp.repository.StudentsRepository;
import com.raj.schoolerp.service.FeeTransactionsService;

@Service
public class FeeTransactionsServiceImpl implements FeeTransactionsService {

	@Autowired
	private FeeTransactionsRepository feeTransactionsRepo;

	@Autowired
	private StudentsRepository studentsRepo;

	@Autowired
	private FeeStructuresRepository feeStructuresRepo;

	@Override
	public FeeTransactions addFeeTransaction(FeeTransactionsDTO dto) throws FeeTransactionsException {

		FeeTransactions newTransaction = new FeeTransactions();

		BeanUtils.copyProperties(dto, newTransaction);

		Students student = studentsRepo.findById(dto.getStudentId())
				.orElseThrow(() -> new FeeTransactionsException("Student Not Found"));

		FeeStructures feeStructure = feeStructuresRepo.findById(dto.getFeeStructId())
				.orElseThrow(() -> new FeeTransactionsException("Fee Structure Not Found"));

		newTransaction.setStudent(student);

		newTransaction.setFeeStructures(feeStructure);

		// Auto Receipt Number
		FeeTransactions lastTransaction = feeTransactionsRepo.findTopByOrderByFeeTransactionsIdDesc().orElse(null);

		String receiptNo;

		if (lastTransaction == null || lastTransaction.getRecieptNo() == null) {

			receiptNo = "0001";
		} else {

			int lastNo = Integer.parseInt(lastTransaction.getRecieptNo());

			receiptNo = String.format("%04d", lastNo + 1);
		}

		newTransaction.setRecieptNo(receiptNo);

		return feeTransactionsRepo.save(newTransaction);
	}

	@Override
	public FeeTransactions updateFeeTransaction(Long feeTransactionsId, FeeTransactionsDTO feeTransactionsDTO)
			throws FeeTransactionsException {

		FeeTransactions existTransaction = feeTransactionsRepo.findById(feeTransactionsId)
				.orElseThrow(() -> new FeeTransactionsException("Fee Transaction Not Found"));

		BeanUtils.copyProperties(feeTransactionsDTO, existTransaction);

		// Student
		Students student = studentsRepo.findById(feeTransactionsDTO.getStudentId())
				.orElseThrow(() -> new FeeTransactionsException("Student Not Found"));

		// Fee Structure
		FeeStructures feeStructure = feeStructuresRepo.findById(feeTransactionsDTO.getFeeStructId())
				.orElseThrow(() -> new FeeTransactionsException("Fee Structure Not Found"));

		existTransaction.setStudent(student);

		existTransaction.setFeeStructures(feeStructure);

		// Auto values
		existTransaction.setAmountDue(feeStructure.getAmount());

		existTransaction.setLateFine(feeStructure.getLateFine());

		existTransaction.setAcademicYear(feeStructure.getAcademicYear());

		return feeTransactionsRepo.save(existTransaction);
	}

	@Override
	public FeeTransactions getFeeTransactionById(Long feeTransactionsId) throws FeeTransactionsException {

		return feeTransactionsRepo.findById(feeTransactionsId)
				.orElseThrow(() -> new FeeTransactionsException("Wrong Fee Transaction Id"));
	}

	@Override
	public List<FeeTransactions> getAllFeeTransactions() throws FeeTransactionsException {

		List<FeeTransactions> transactions = feeTransactionsRepo.findAll();

		if (transactions.isEmpty()) {

			throw new FeeTransactionsException("No Fee Transactions Found");
		}

		return transactions;
	}

	@Override
	public List<FeeTransactions> getFeeTransactionsByStudentId(Long studentId) throws FeeTransactionsException {

		List<FeeTransactions> transactions = feeTransactionsRepo.findFeeTransactionsByStudentStudentId(studentId);

		if (transactions.isEmpty()) {

			throw new FeeTransactionsException("No Fee Transactions Found For Student Id : " + studentId);
		}

		return transactions;
	}

	@Override
	public List<FeeTransactions> getFeeTransactionsByStatus(FeeTransactionStatus status)
			throws FeeTransactionsException {

		List<FeeTransactions> transactions = feeTransactionsRepo.findByFeeTransactionStatus(status);

		if (transactions.isEmpty()) {

			throw new FeeTransactionsException("No Fee Transactions Found");
		}

		return transactions;
	}

	@Override
	public List<FeeTransactions> getParentFees(Long parentId) throws FeeTransactionsException {

		List<FeeTransactions> fees = feeTransactionsRepo.getParentFees(parentId);

		if (fees.isEmpty()) {
			throw new FeeTransactionsException("No Fee Records Found For Parent Id: " + parentId);
		}

		return fees;
	}
}