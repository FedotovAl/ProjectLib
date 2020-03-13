package com.example.library.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Books {
    private Long id;
    private String name;
    private Long authorId;
    private String categoryId;
}
