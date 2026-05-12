package com.raj.schoolerp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksDTO {

    private String title;

    private String author;

    private String isbn;

    private String publisher;

    private String edition;

    private String category;

    private Integer totalCopies;

    private Integer availableCopies;

    private String shelfLocation;
}