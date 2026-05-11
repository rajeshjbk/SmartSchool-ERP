package com.raj.schoolerp.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book_issues")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class BookIssues {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookIssuesId;
	
	@ManyToOne
	@JoinColumn(name = "bookId")
	private Set<Books> books;

	@ManyToOne
	@JoinColumn(name = "studentId")
	private Students student;
	
	@ManyToOne
	@JoinColumn(name ="issuedBy" )
	private Students issuedBy;
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDate issueDate;
	
	@NonNull
	private LocalDate dueDate;
	
	@NonNull
	private LocalDate returnDate;
	
	@NonNull
	private Double fineAmount;
	
	@NonNull
	private Boolean finePaid;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private BookStatus  bookStatus ;
	
}
