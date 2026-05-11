package com.raj.schoolerp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.entity.BookIssues;

@Repository
public interface BookIssuesRepository extends JpaRepository<BookIssues, Long> {

}
