package com.example.library.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Authors {
    private Long id;
    private String firstname;
    private String lastname;
}
