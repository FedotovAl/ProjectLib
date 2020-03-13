package com.example.library.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BooksAuthors {
    private Long booksId;
    private Long authorsId;
}
