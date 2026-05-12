package com.raj.schoolerp.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.schoolerp.DTO.BooksDTO;
import com.raj.schoolerp.exception.BooksException;
import com.raj.schoolerp.model.Books;
import com.raj.schoolerp.repository.BooksRepository;
import com.raj.schoolerp.service.BooksService;

@Service
public class BooksServiceImpl implements BooksService {

	@Autowired
	private BooksRepository booksRepo;

	@Override
	public Books addBook(BooksDTO booksDTO) throws BooksException {

		Books newBook = new Books();

		BeanUtils.copyProperties(booksDTO, newBook);

		return booksRepo.save(newBook);
	}

	@Override
	public Books updateBook(Long bookId, BooksDTO booksDTO) throws BooksException {

		Books existBook = booksRepo.findById(bookId).orElseThrow(() -> new BooksException("Book Not Found"));

		BeanUtils.copyProperties(booksDTO, existBook);

		return booksRepo.save(existBook);
	}

	@Override
	public String deleteBook(Long bookId) throws BooksException {

		booksRepo.findById(bookId).orElseThrow(() -> new BooksException("Book Not Found"));

		booksRepo.deleteById(bookId);

		return "Book deleted with ID: " + bookId;
	}

	@Override
	public Books getBookById(Long bookId) throws BooksException {

		return booksRepo.findById(bookId).orElseThrow(() -> new BooksException("Wrong Book Id"));
	}

	@Override
	public List<Books> getAllBooks() throws BooksException {

		return booksRepo.findAll();
	}

	@Override
	public Books getBookByIsbn(String isbn) throws BooksException {

		return booksRepo.findBookByIsbn(isbn).orElseThrow(() -> new BooksException("Wrong ISBN Number"));
	}

	@Override
	public List<Books> getBooksByCategory(String category) throws BooksException {

		List<Books> books = booksRepo.findBooksByCategory(category);

		if (books.isEmpty()) {

			throw new BooksException("No Books Found In Category");
		}

		return books;
	}

	@Override
	public List<Books> getBooksByAuthor(String author) throws BooksException {

		List<Books> books = booksRepo.findBooksByAuthor(author);

		if (books.isEmpty()) {

			throw new BooksException("No Books Found By Author");
		}

		return books;
	}

	@Override
	public List<Books> getAvailableBooks() throws BooksException {

		List<Books> books = booksRepo.findAvailableBooks();

		if (books.isEmpty()) {

			throw new BooksException("No Available Books Found");
		}

		return books;
	}
}