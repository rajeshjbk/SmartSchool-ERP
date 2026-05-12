package com.raj.schoolerp.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.BookIssuesDTO;
import com.raj.schoolerp.exception.BookIssuesException;
import com.raj.schoolerp.model.BookIssues;
import com.raj.schoolerp.model.BookStatus;
import com.raj.schoolerp.repository.BookIssuesRepository;
import com.raj.schoolerp.service.BookIssuesService;

@Service
public class BookIssuesServiceImpl implements BookIssuesService {

	@Autowired
	private BookIssuesRepository bookIssuesRepo;

	@Override
	public BookIssues issueBook(BookIssuesDTO bookIssuesDTO) throws BookIssuesException {

		BookIssues newBookIssue = new BookIssues();

		BeanUtils.copyProperties(bookIssuesDTO, newBookIssue);

		return bookIssuesRepo.save(newBookIssue);
	}

	@Override
	public BookIssues updateBookIssue(Long bookIssuesId, BookIssuesDTO bookIssuesDTO) throws BookIssuesException {

		BookIssues existBookIssue = bookIssuesRepo.findById(bookIssuesId)
				.orElseThrow(() -> new BookIssuesException("Book Issue Not Found"));

		BeanUtils.copyProperties(bookIssuesDTO, existBookIssue);

		return bookIssuesRepo.save(existBookIssue);
	}

	@Override
	public String deleteBookIssue(Long bookIssuesId) throws BookIssuesException {

		bookIssuesRepo.findById(bookIssuesId).orElseThrow(() -> new BookIssuesException("Book Issue Not Found"));

		bookIssuesRepo.deleteById(bookIssuesId);

		return "Book Issue deleted with ID: " + bookIssuesId;
	}

	@Override
	public BookIssues getBookIssueById(Long bookIssuesId) throws BookIssuesException {

		return bookIssuesRepo.findById(bookIssuesId).orElseThrow(() -> new BookIssuesException("Wrong Book Issue Id"));
	}

	@Override
	public List<BookIssues> getAllBookIssues() throws BookIssuesException {

		return bookIssuesRepo.findAll();
	}

	@Override
	public List<BookIssues> getBookIssuesByStudentId(Long studentId) throws BookIssuesException {

		List<BookIssues> bookIssues = bookIssuesRepo.findBookIssuesByStudentId(studentId);

		if (bookIssues.isEmpty()) {

			throw new BookIssuesException("No Book Issues Found");
		}

		return bookIssues;
	}

	@Override
	public List<BookIssues> getBookIssuesByBookId(Long bookId) throws BookIssuesException {

		List<BookIssues> bookIssues = bookIssuesRepo.findBookIssuesByBookId(bookId);

		if (bookIssues.isEmpty()) {

			throw new BookIssuesException("No Book Issues Found");
		}

		return bookIssues;
	}

	@Override
	public List<BookIssues> getOverdueBooks() throws BookIssuesException {

		List<BookIssues> bookIssues = bookIssuesRepo.findOverdueBooks();

		if (bookIssues.isEmpty()) {

			throw new BookIssuesException("No Overdue Books Found");
		}

		return bookIssues;
	}

	@Override
	public List<BookIssues> getBookIssuesByStatus(BookStatus bookStatus) throws BookIssuesException {

		List<BookIssues> bookIssues = bookIssuesRepo.findBookIssuesByStatus(bookStatus);

		if (bookIssues.isEmpty()) {

			throw new BookIssuesException("No Book Issues Found");
		}

		return bookIssues;
	}

	@Override
	public List<BookIssues> getBookIssuesByDueDate(LocalDate dueDate) throws BookIssuesException {

		List<BookIssues> bookIssues = bookIssuesRepo.findBookIssuesByDueDate(dueDate);

		if (bookIssues.isEmpty()) {

			throw new BookIssuesException("No Book Issues Found");
		}

		return bookIssues;
	}
}