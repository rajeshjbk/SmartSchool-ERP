package com.raj.schoolerp.DTO;

import java.time.LocalDate;

import com.raj.schoolerp.model.FeeTransactionStatus;
import com.raj.schoolerp.model.PaymentMode;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class FeeTransactionsDTO {
	
	@NonNull
	private Double amountDue;
	
	@NonNull
	private Double lateFine;
		
	@NonNull
	private Double amountPaid;
	
	@NonNull
	private LocalDate dueDate;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private PaymentMode paymentMode;
	
	@NonNull
	private String transactionId;
	
	@NonNull
	private String recieptNo;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private FeeTransactionStatus  feeTransactionStatus;
	
	@NonNull
	private String academicYear;
	
	private String collectedBy;
	
}
