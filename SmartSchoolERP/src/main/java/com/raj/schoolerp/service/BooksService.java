package com.raj.schoolerp.service;

import java.util.List;

import com.raj.schoolerp.DTO.BooksDTO;
import com.raj.schoolerp.exception.BooksException;
import com.raj.schoolerp.model.Books;

public interface BooksService {

	// Add Book
	Books addBook(BooksDTO booksDTO) throws BooksException;

	// Update Book
	Books updateBook(Long bookId, BooksDTO booksDTO) throws BooksException;

	// Delete Book
	String deleteBook(Long bookId) throws BooksException;

	// Get Book By Id
	Books getBookById(Long bookId) throws BooksException;

	// Get All Books
	List<Books> getAllBooks() throws BooksException;

	// Get Book By ISBN
	Books getBookByIsbn(String isbn) throws BooksException;

	// Get Books By Category
	List<Books> getBooksByCategory(String category) throws BooksException;

	// Get Books By Author
	List<Books> getBooksByAuthor(String author) throws BooksException;

	// Get Available Books
	List<Books> getAvailableBooks() throws BooksException;
}