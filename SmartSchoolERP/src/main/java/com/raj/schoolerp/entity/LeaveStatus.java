package com.raj.schoolerp.entity;

public enum LeaveStatus {

	APPLIED,        // Leave request submitted

	PENDING,        // Waiting for approval

	APPROVED,       // Approved by authority

	REJECTED,       // Rejected by authority

	CANCELLED,      // Cancelled by employee

	IN_PROGRESS,    // Leave currently ongoing

	COMPLETED       // Leave period finished
}