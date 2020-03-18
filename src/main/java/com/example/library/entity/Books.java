package com.example.library.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Books {
    private Long id;
    private String name;
    private String category;
}
