package com.raj.schoolerp.entity;

public enum FeeTransactionStatus {

	INITIATED,     // Payment started but not completed

	PENDING,       // Awaiting confirmation (e.g., bank processing)

	SUCCESS,       // Payment completed successfully

	FAILED,        // Payment failed

	CANCELLED,     // User cancelled transaction

	REFUNDED,      // Amount refunded

	PARTIAL        // Partial payment done
}