package com.raj.schoolerp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.raj.schoolerp.DTO.BookIssuesDTO;
import com.raj.schoolerp.exception.BookIssuesException;
import com.raj.schoolerp.model.BookIssues;
import com.raj.schoolerp.model.BookStatus;
import com.raj.schoolerp.service.BookIssuesService;

@RestController
@RequestMapping("/schoolerp/book-issues")
@CrossOrigin("*")
public class BookIssuesController {

	@Autowired
	private BookIssuesService bookIssuesService;

	@PostMapping("/issue")
	public BookIssues issueBook(@RequestBody BookIssuesDTO bookIssuesDTO) throws BookIssuesException {

		return bookIssuesService.issueBook(bookIssuesDTO);
	}

	@PutMapping("/update/{bookIssuesId}")
	public BookIssues updateBookIssue(@PathVariable Long bookIssuesId, @RequestBody BookIssuesDTO bookIssuesDTO)
			throws BookIssuesException {

		return bookIssuesService.updateBookIssue(bookIssuesId, bookIssuesDTO);
	}

	@DeleteMapping("/delete/{bookIssuesId}")
	public String deleteBookIssue(@PathVariable Long bookIssuesId) throws BookIssuesException {

		return bookIssuesService.deleteBookIssue(bookIssuesId);
	}

	@GetMapping("/{bookIssuesId}")
	public BookIssues getBookIssueById(@PathVariable Long bookIssuesId) throws BookIssuesException {

		return bookIssuesService.getBookIssueById(bookIssuesId);
	}

	@GetMapping("/all")
	public List<BookIssues> getAllBookIssues() throws BookIssuesException {

		return bookIssuesService.getAllBookIssues();
	}

	@GetMapping("/student/{studentId}")
	public List<BookIssues> getBookIssuesByStudentId(@PathVariable Long studentId) throws BookIssuesException {

		return bookIssuesService.getBookIssuesByStudentId(studentId);
	}

	@GetMapping("/book/{bookId}")
	public List<BookIssues> getBookIssuesByBookId(@PathVariable Long bookId) throws BookIssuesException {

		return bookIssuesService.getBookIssuesByBookId(bookId);
	}

	@GetMapping("/overdue")
	public List<BookIssues> getOverdueBooks() throws BookIssuesException {

		return bookIssuesService.getOverdueBooks();
	}

	@GetMapping("/status/{bookStatus}")
	public List<BookIssues> getBookIssuesByStatus(@PathVariable BookStatus bookStatus) throws BookIssuesException {

		return bookIssuesService.getBookIssuesByStatus(bookStatus);
	}

	@GetMapping("/due-date")
	public List<BookIssues> getBookIssuesByDueDate(@RequestParam LocalDate dueDate) throws BookIssuesException {

		return bookIssuesService.getBookIssuesByDueDate(dueDate);
	}
}