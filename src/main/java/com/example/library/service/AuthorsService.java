package com.example.library.service;

import com.example.library.dao.AuthorsDAO;
import com.example.library.dao.BooksAuthorsDAO;
import com.example.library.entity.Authors;

import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;

public class AuthorsService {
    private static final Logger logger = Logger.getLogger(AuthorsService.class);

    //добавление автора
    public void addNewAuthor(Authors authors){
        logger.info("Add new author");
        AuthorsDAO authorsDAO = new AuthorsDAO();
        try {
            authorsDAO.add(authors);
            System.out.println("Author is created");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    //получение сущности автора по ID
    public Authors getAuthorByID(Long id){
        logger.info("Get author by ID");
        AuthorsDAO authorsDAO = new AuthorsDAO();
        Authors authors = new Authors();
        try {
            authors = authorsDAO.getByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
        if (authors.getId() == null){
            System.out.println("Author does not exist");
            return null;
        } else {
            return authors;
        }
    }
    //удаление автора  и всех записей с ним из промежуточной таблицы
    public void removeAuthor(Authors authors){
        logger.info("Delete author");
        AuthorsDAO authorsDAO = new AuthorsDAO();
        BooksAuthorsDAO booksAuthorsDAO = new BooksAuthorsDAO();
        try {
            if (authorsDAO.getByID(authors.getId()).getId() != null){
                if (booksAuthorsDAO.getAllByAuthorID(authors.getId()).size() != 0) {
                    booksAuthorsDAO.removeByAuthorID(authors.getId());
                }
                authorsDAO.remove(authors);
                System.out.println("Removing is completed");
            } else{
                System.out.println("Author does not exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    //изменение автора
    public void updateAuthor(Authors authors){
        logger.info("Update author");
        AuthorsDAO authorsDAO = new AuthorsDAO();
        try {
            if (authorsDAO.getByID(authors.getId()).getId() != null){
                authorsDAO.update(authors);
                System.out.println("Updating is complited");
            } else{
                System.out.println("Author does not exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    //вывод на экран списка всех авторов
    public void showAllAuthors(){
        logger.info("Show all authors");
        AuthorsDAO authorsDAO = new AuthorsDAO();
        try {
            List<Authors> authorsList = authorsDAO.getAll();
            if (authorsList.size() != 0) {
                for (Authors a : authorsList) {
                    System.out.println(a);
                }
            } else {
                System.out.println("authorsList is empty");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }
}

