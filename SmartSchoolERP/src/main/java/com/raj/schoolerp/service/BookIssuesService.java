package com.raj.schoolerp.service;

import java.time.LocalDate;
import java.util.List;

import com.raj.schoolerp.DTO.BookIssuesDTO;
import com.raj.schoolerp.exception.BookIssuesException;
import com.raj.schoolerp.model.BookIssues;
import com.raj.schoolerp.model.BookStatus;

public interface BookIssuesService {

	// Issue Book
	BookIssues issueBook(BookIssuesDTO bookIssuesDTO) throws BookIssuesException;

	// Update Book Issue
	BookIssues updateBookIssue(Long bookIssuesId, BookIssuesDTO bookIssuesDTO) throws BookIssuesException;

	// Delete Book Issue
	String deleteBookIssue(Long bookIssuesId) throws BookIssuesException;

	// Get Book Issue By Id
	BookIssues getBookIssueById(Long bookIssuesId) throws BookIssuesException;

	// Get All Book Issues
	List<BookIssues> getAllBookIssues() throws BookIssuesException;

	// Get Book Issues By Student Id
	List<BookIssues> getBookIssuesByStudentId(Long studentId) throws BookIssuesException;

	// Get Book Issues By Book Id
	List<BookIssues> getBookIssuesByBookId(Long bookId) throws BookIssuesException;

	// Get Overdue Books
	List<BookIssues> getOverdueBooks() throws BookIssuesException;

	// Get Book Issues By Status
	List<BookIssues> getBookIssuesByStatus(BookStatus bookStatus) throws BookIssuesException;

	// Get Book Issues By Due Date
	List<BookIssues> getBookIssuesByDueDate(LocalDate dueDate) throws BookIssuesException;
}