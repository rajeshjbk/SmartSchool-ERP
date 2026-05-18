package com.raj.schoolerp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.model.BookIssues;
import com.raj.schoolerp.model.BookStatus;

@Repository
public interface BookIssuesRepository extends JpaRepository<BookIssues, Long> {

	// Get Book Issues By Student Id
	@Query("SELECT bi FROM BookIssues bi " + "WHERE bi.student.studentId = :studentId")
	List<BookIssues> findBookIssuesByStudentId(@Param("studentId") Long studentId);

	// Get Book Issues By Book Id
	@Query("SELECT bi FROM BookIssues bi " + "WHERE bi.book.bookId = :bookId")
	List<BookIssues> findBookIssuesByBookId(@Param("bookId") Long bookId);

	// Get Overdue Books
	@Query("SELECT bi FROM BookIssues bi " + "WHERE bi.dueDate < CURRENT_DATE " + "AND bi.bookStatus = 'ISSUED'")
	List<BookIssues> findOverdueBooks();

	// Get Book Issues By Status
	@Query("SELECT bi FROM BookIssues bi " + "WHERE bi.bookStatus = :bookStatus")
	List<BookIssues> findBookIssuesByStatus(@Param("bookStatus") BookStatus bookStatus);

	// Get Book Issues By Due Date
	@Query("SELECT bi FROM BookIssues bi " + "WHERE bi.dueDate = :dueDate")
	List<BookIssues> findBookIssuesByDueDate(@Param("dueDate") LocalDate dueDate);

	// Student Dashboard
	@Query("""
			SELECT b
			FROM BookIssues b
			WHERE b.student.user.userId = :userId
			""")
	List<BookIssues> getStudentBooks(@Param("userId") Long userId);

	// Parent Dashboard
	@Query("""
			SELECT b
			FROM BookIssues b
			WHERE b.student.parent.userId = :parentId
			""")
	List<BookIssues> getParentBooks(@Param("parentId") Long parentId);
}
