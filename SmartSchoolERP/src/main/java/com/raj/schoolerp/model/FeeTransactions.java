package com.raj.schoolerp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "fee_transactions")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class FeeTransactions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long feeTransactionsId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "studentId")
	@JsonIgnoreProperties({ "user", "attendances", "results" })
	private Students student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "feeStructId")
	@JsonIgnoreProperties({ "feeTransactions", "classes" })
	private FeeStructures feeStructures;

	@NonNull
	private Double amountDue;

	
	private Double lateFine;

	private Double amountPaid;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate paymentDate;

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
	private FeeTransactionStatus feeTransactionStatus;

	@NonNull
	private String academicYear;

	@NonNull
	private String collectedBy;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;
}