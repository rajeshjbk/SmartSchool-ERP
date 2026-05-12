package com.raj.schoolerp.DTO;

import java.time.LocalDate;

import com.raj.schoolerp.model.BookStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookIssuesDTO {

    private Long bookId;

    private Long studentId;

    private Long issuedBy;

    private LocalDate dueDate;

    private LocalDate returnDate;

    private Double fineAmount;

    private Boolean finePaid;

    private BookStatus bookStatus;
}