package com.raj.schoolerp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raj.schoolerp.DTO.BooksDTO;
import com.raj.schoolerp.exception.BooksException;
import com.raj.schoolerp.model.Books;
import com.raj.schoolerp.service.BooksService;

@RestController
@RequestMapping("/schoolerp/books")
@CrossOrigin("*")
public class BooksController {

	@Autowired
	private BooksService booksService;

	@PostMapping("/add")
	public Books addBook(@RequestBody BooksDTO booksDTO) throws BooksException {

		return booksService.addBook(booksDTO);
	}

	@PutMapping("/update/{bookId}")
	public Books updateBook(@PathVariable Long bookId, @RequestBody BooksDTO booksDTO) throws BooksException {

		return booksService.updateBook(bookId, booksDTO);
	}

	@DeleteMapping("/delete/{bookId}")
	public String deleteBook(@PathVariable Long bookId) throws BooksException {

		return booksService.deleteBook(bookId);
	}

	@GetMapping("/{bookId}")
	public Books getBookById(@PathVariable Long bookId) throws BooksException {

		return booksService.getBookById(bookId);
	}

	@GetMapping("/all")
	public List<Books> getAllBooks() throws BooksException {

		return booksService.getAllBooks();
	}

	@GetMapping("/isbn/{isbn}")
	public Books getBookByIsbn(@PathVariable String isbn) throws BooksException {

		return booksService.getBookByIsbn(isbn);
	}

	@GetMapping("/category/{category}")
	public List<Books> getBooksByCategory(@PathVariable String category) throws BooksException {

		return booksService.getBooksByCategory(category);
	}

	@GetMapping("/author/{author}")
	public List<Books> getBooksByAuthor(@PathVariable String author) throws BooksException {

		return booksService.getBooksByAuthor(author);
	}

	@GetMapping("/available")
	public List<Books> getAvailableBooks() throws BooksException {

		return booksService.getAvailableBooks();
	}
}