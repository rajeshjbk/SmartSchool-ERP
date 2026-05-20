package com.raj.schoolerp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.model.FeeTransactionStatus;
import com.raj.schoolerp.model.FeeTransactions;

@Repository
public interface FeeTransactionsRepository extends JpaRepository<FeeTransactions, Long> {

	// Get Fee Transactions By Student Id
	@Query("SELECT ft FROM FeeTransactions ft " + "WHERE ft.student.studentId = :studentId")
	List<FeeTransactions> findFeeTransactionsByStudentId(@Param("studentId") Long studentId);

	// Get Fee Transactions By Status
	@Query("SELECT ft FROM FeeTransactions ft " + "WHERE ft.feeTransactionStatus = :status")
	List<FeeTransactions> findFeeTransactionsByStatus(@Param("status") FeeTransactionStatus status);

	// Student Dashboard
	@Query("""
			SELECT f
			FROM FeeTransactions f
			WHERE f.student.user.userId = :userId
			""")
	List<FeeTransactions> getStudentFees(@Param("userId") Long userId);

	// Parent Dashboard
	@Query("""
			SELECT f
			FROM FeeTransactions f
			WHERE f.student.parent.userId = :parentId
			""")
	List<FeeTransactions> getParentFees(@Param("parentId") Long parentId);

	List<FeeTransactions> findFeeTransactionsByStudentStudentId(Long studentId);

	List<FeeTransactions> findByFeeTransactionStatus(FeeTransactionStatus feeTransactionStatus);

	Optional<FeeTransactions> findTopByOrderByFeeTransactionsIdDesc();
}
