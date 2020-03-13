package com.example.library.domain;

import lombok.Data;

@Data
public class Books {
    private Long id;
    private String name;
    private Long authorId;
    private String categoryId;
}
