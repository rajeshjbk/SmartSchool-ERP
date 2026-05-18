package com.raj.schoolerp.model;

import java.time.LocalDate;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Books {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long bookId;

    @NonNull
    @Column(length = 30)
    private String title;

    @NonNull
    @Column(length = 30)
    private String author;

    @NonNull
    @Column(length = 30,
            unique = true)
    private String isbn;

    @NonNull
    @Column(length = 30)
    private String publisher;

    @NonNull
    @Column(length = 30)
    private String edition;

    @NonNull
    @Column(length = 30)
    private String category;

    @NonNull
    private Integer totalCopies;

    @NonNull
    private Integer availableCopies;

    @NonNull
    @Column(length = 30)
    private String shelfLocation;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate addedDate;

    @OneToMany(mappedBy = "book",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<BookIssues> bookIssues;
}