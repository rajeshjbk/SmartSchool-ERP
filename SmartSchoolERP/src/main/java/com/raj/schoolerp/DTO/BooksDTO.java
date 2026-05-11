package com.raj.schoolerp.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksDTO {
	
	@NonNull
	private String title;
	
	@NonNull
	private String author;
	
	@NonNull
	private String isbn;
	
	@NonNull
	private String publisher;
	
	@NonNull
	private String edition;
	
	@NonNull
	private String category;
	
	@NonNull
	private Integer totalCopies;
	
	@NonNull
	private Integer availableCopies;
	
	@NonNull
	private String shelfLocation;
	
}
