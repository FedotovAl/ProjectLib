package com.example.library.controller;

import com.example.library.entity.Authors;
import com.example.library.entity.Books;
import com.example.library.service.BooksService;

import java.util.List;

public class BookController {
    BooksService booksService = new BooksService();

    public void addNewBook(Books books, List<Authors> authorsList){
        booksService.addNewBook(books, authorsList);
    }

    public void removeBook(Long id){
        booksService.removeBook(booksService.getBooksByID(id));
    }

    public void removeBook(String name){
        booksService.removeBook(booksService.getBooksByName(name));
    }

    public void updateBook(Books books) {
        booksService.updateBook(books);
    }

    public Books getBookByID(Long id) {
        return booksService.getBooksByID(id);
    }

    public Books getBookByName(String name) {
        return booksService.getBooksByName(name);
    }

    public void showAllBooks(){
        booksService.showAllBooks();
    }

    public void printBook(Books books){
        booksService.printBook(books);
    }

    public void printAllBooksByAuthor(Authors authors){
        booksService.printAllBooksByAuthor(authors);
    }
}
