package com.raj.schoolerp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raj.schoolerp.DTO.FeeTransactionsDTO;
import com.raj.schoolerp.exception.FeeTransactionsException;
import com.raj.schoolerp.model.FeeTransactionStatus;
import com.raj.schoolerp.model.FeeTransactions;
import com.raj.schoolerp.service.FeeTransactionsService;

@RestController
@RequestMapping("/schoolerp/fee-transactions")
@CrossOrigin(origins = "http://localhost:5173")
public class FeeTransactionsController {

	@Autowired
	private FeeTransactionsService feeTransactionsService;

	@PostMapping("/add")
	public FeeTransactions addFeeTransaction(@RequestBody FeeTransactionsDTO feeTransactionsDTO)
			throws FeeTransactionsException {

		return feeTransactionsService.addFeeTransaction(feeTransactionsDTO);
	}

	@PutMapping("/update/{feeTransactionsId}")
	public FeeTransactions updateFeeTransaction(@PathVariable Long feeTransactionsId,
			@RequestBody FeeTransactionsDTO feeTransactionsDTO) throws FeeTransactionsException {

		return feeTransactionsService.updateFeeTransaction(feeTransactionsId, feeTransactionsDTO);
	}

	@GetMapping("/{feeTransactionsId}")
	public FeeTransactions getFeeTransactionById(@PathVariable Long feeTransactionsId) throws FeeTransactionsException {

		return feeTransactionsService.getFeeTransactionById(feeTransactionsId);
	}

	@GetMapping("/all")
	public List<FeeTransactions> getAllFeeTransactions() throws FeeTransactionsException {

		return feeTransactionsService.getAllFeeTransactions();
	}

	@GetMapping("/student/{studentId}")
	public List<FeeTransactions> getFeeTransactionsByStudentId(@PathVariable Long studentId)
			throws FeeTransactionsException {

		return feeTransactionsService.getFeeTransactionsByStudentId(studentId);
	}

	@GetMapping("/status/{status}")
	public List<FeeTransactions> getFeeTransactionsByStatus(@PathVariable FeeTransactionStatus status)
			throws FeeTransactionsException {

		return feeTransactionsService.getFeeTransactionsByStatus(status);
	}

	@GetMapping("/parent/{parentId}")
	public ResponseEntity<List<FeeTransactions>> getParentFeesHandler(@PathVariable Long parentId)
			throws FeeTransactionsException {

		List<FeeTransactions> fees = feeTransactionsService.getParentFees(parentId);

		return new ResponseEntity<>(fees, HttpStatus.OK);
	}
}