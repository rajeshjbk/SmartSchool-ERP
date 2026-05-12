package com.raj.schoolerp.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "book_issues")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class BookIssues {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long bookIssuesId;

    @ManyToOne
    @JoinColumn(name = "bookId")
    @JsonIgnoreProperties({
            "bookIssues"
    })
    private Books books;

    @ManyToOne
    @JoinColumn(name = "studentId")
    @JsonIgnoreProperties({
            "user",
            "attendances",
            "results"
    })
    private Students student;

    @ManyToOne
    @JoinColumn(name = "issuedBy")
    @JsonIgnoreProperties({
            "user",
            "attendances",
            "results"
    })
    private Students issuedBy;

    @CreationTimestamp
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
    private BookStatus bookStatus;
}