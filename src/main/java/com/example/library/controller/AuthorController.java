package com.example.library.controller;

import com.example.library.entity.Authors;
import com.example.library.service.AuthorsService;

public class AuthorController {
    AuthorsService authorsService = new AuthorsService();

    public void addNewAuthor(Authors authors){
        authorsService.addNewAuthor(authors);
    }

    public void removeAuthor(Long id){
        authorsService.removeAuthor(authorsService.getAuthorByID(id));
    }

    public void updateAuthor(Authors authors){
        authorsService.updateAuthor(authors);
    }

    public Authors getAuthorByID(Long id){
        return authorsService.getAuthorByID(id);
    }

    public void showAllAuthors(){
        authorsService.showAllAuthors();
    }
}
