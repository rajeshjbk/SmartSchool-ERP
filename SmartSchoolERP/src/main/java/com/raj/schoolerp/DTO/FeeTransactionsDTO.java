package com.raj.schoolerp.DTO;

import java.time.LocalDate;

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
	private LocalDate paymentDate;
	
	@NonNull
	private LocalDate dueDate;
	
	@NonNull
	private String paymentMode;
	
	@NonNull
	private String transactionId;
	
	@NonNull
	private String recieptNo;
	
	@NonNull
	private String  feeTransactionStatus;
	
	@NonNull
	private String academicYear;
	
	private Integer collectedBy;
	
}
