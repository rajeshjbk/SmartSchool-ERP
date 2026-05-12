package com.raj.schoolerp.DTO;

import java.time.LocalDate;

import com.raj.schoolerp.model.FeeTransactionStatus;
import com.raj.schoolerp.model.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeeTransactionsDTO {

	private Long studentId;

	private Long feeStructId;

	private Double amountDue;

	private Double lateFine;

	private Double amountPaid;

	private LocalDate dueDate;

	private PaymentMode paymentMode;

	private String transactionId;

	private String recieptNo;

	private FeeTransactionStatus feeTransactionStatus;

	private String academicYear;

	private String collectedBy;
}