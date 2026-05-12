package com.raj.schoolerp.service;

import java.util.List;

import com.raj.schoolerp.DTO.FeeTransactionsDTO;
import com.raj.schoolerp.exception.FeeTransactionsException;
import com.raj.schoolerp.model.FeeTransactionStatus;
import com.raj.schoolerp.model.FeeTransactions;

public interface FeeTransactionsService {

	// Add Fee Transaction
	FeeTransactions addFeeTransaction(FeeTransactionsDTO feeTransactionsDTO) throws FeeTransactionsException;

	// Update Fee Transaction
	FeeTransactions updateFeeTransaction(Long feeTransactionsId, FeeTransactionsDTO feeTransactionsDTO)
			throws FeeTransactionsException;

	// Get Fee Transaction By Id
	FeeTransactions getFeeTransactionById(Long feeTransactionsId) throws FeeTransactionsException;

	// Get All Fee Transactions
	List<FeeTransactions> getAllFeeTransactions() throws FeeTransactionsException;

	// Get Fee Transactions By Student Id
	List<FeeTransactions> getFeeTransactionsByStudentId(Long studentId) throws FeeTransactionsException;

	// Get Fee Transactions By Status
	List<FeeTransactions> getFeeTransactionsByStatus(FeeTransactionStatus status) throws FeeTransactionsException;
}