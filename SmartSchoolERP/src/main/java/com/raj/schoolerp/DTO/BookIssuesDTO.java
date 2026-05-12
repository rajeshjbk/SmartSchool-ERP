package com.raj.schoolerp.DTO;

import java.time.LocalDate;

import com.raj.schoolerp.model.BookStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class BookIssuesDTO {
	
	@NonNull
	private LocalDate dueDate;
	
	@NonNull
	private LocalDate returnDate;
	
	private Double fineAmount;
	
	private Boolean finePaid;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private BookStatus  bookStatus ;
}
