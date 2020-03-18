package com.example.library.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BooksAuthors {
    private Long booksId;
    private Long authorsId;
}
