package com.raj.schoolerp.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.FeeTransactionsDTO;
import com.raj.schoolerp.exception.FeeTransactionsException;
import com.raj.schoolerp.model.FeeTransactionStatus;
import com.raj.schoolerp.model.FeeTransactions;
import com.raj.schoolerp.repository.FeeTransactionsRepository;
import com.raj.schoolerp.service.FeeTransactionsService;

@Service
public class FeeTransactionsServiceImpl implements FeeTransactionsService {

	@Autowired
	private FeeTransactionsRepository feeTransactionsRepo;

	@Override
	public FeeTransactions addFeeTransaction(FeeTransactionsDTO feeTransactionsDTO) throws FeeTransactionsException {

		FeeTransactions newTransaction = new FeeTransactions();

		BeanUtils.copyProperties(feeTransactionsDTO, newTransaction);

		return feeTransactionsRepo.save(newTransaction);
	}

	@Override
	public FeeTransactions updateFeeTransaction(Long feeTransactionsId, FeeTransactionsDTO feeTransactionsDTO)
			throws FeeTransactionsException {

		FeeTransactions existTransaction = feeTransactionsRepo.findById(feeTransactionsId)
				.orElseThrow(() -> new FeeTransactionsException("Fee Transaction Not Found"));

		BeanUtils.copyProperties(feeTransactionsDTO, existTransaction);

		return feeTransactionsRepo.save(existTransaction);
	}

	@Override
	public FeeTransactions getFeeTransactionById(Long feeTransactionsId) throws FeeTransactionsException {

		return feeTransactionsRepo.findById(feeTransactionsId)
				.orElseThrow(() -> new FeeTransactionsException("Wrong Fee Transaction Id"));
	}

	@Override
	public List<FeeTransactions> getAllFeeTransactions() throws FeeTransactionsException {

		return feeTransactionsRepo.findAll();
	}

	@Override
	public List<FeeTransactions> getFeeTransactionsByStudentId(Long studentId) throws FeeTransactionsException {

		List<FeeTransactions> transactions = feeTransactionsRepo.findFeeTransactionsByStudentId(studentId);

		if (transactions.isEmpty()) {

			throw new FeeTransactionsException("No Fee Transactions Found");
		}

		return transactions;
	}

	@Override
	public List<FeeTransactions> getFeeTransactionsByStatus(FeeTransactionStatus status)
			throws FeeTransactionsException {

		List<FeeTransactions> transactions = feeTransactionsRepo.findFeeTransactionsByStatus(status);

		if (transactions.isEmpty()) {

			throw new FeeTransactionsException("No Fee Transactions Found");
		}

		return transactions;
	}
}