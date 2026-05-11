package com.raj.schoolerp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.entity.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {

}
