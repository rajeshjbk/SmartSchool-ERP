package com.raj.schoolerp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.model.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {

	// Get Book By ISBN
	@Query("SELECT b FROM Books b " + "WHERE b.isbn = :isbn")
	Optional<Books> findBookByIsbn(@Param("isbn") String isbn);

	// Get Books By Category
	@Query("SELECT b FROM Books b " + "WHERE b.category = :category")
	List<Books> findBooksByCategory(@Param("category") String category);

	// Get Books By Author
	@Query("SELECT b FROM Books b " + "WHERE b.author = :author")
	List<Books> findBooksByAuthor(@Param("author") String author);

	// Get Available Books
	@Query("SELECT b FROM Books b " + "WHERE b.availableCopies > 0")
	List<Books> findAvailableBooks();
}
