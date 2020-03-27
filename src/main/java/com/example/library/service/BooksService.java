package com.example.library.service;

import com.example.library.dao.AccountingRecordsDAO;
import com.example.library.dao.AuthorsDAO;
import com.example.library.dao.BooksAuthorsDAO;
import com.example.library.dao.BooksDAO;
import com.example.library.entity.AccountingRecords;
import com.example.library.entity.Authors;
import com.example.library.entity.Books;
import com.example.library.entity.BooksAuthors;

import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;

public class BooksService {
    private static final Logger logger = Logger.getLogger(BooksService.class);

    /*
    Добавление новой книги:
    Добавление книги;
    В случае, если автора не существует, создание автора;
    Добавление связей в промежуточную таблицу между книгой и автором;
    Есть возможность не указывать авторов книги (например, если это народные сказки)
    */
    public void addNewBook(Books books, List<Authors> authorsOfBookList){
        logger.info("Add new book");
        BooksDAO booksDAO = new BooksDAO();
        BooksAuthorsDAO booksAuthorsDAO = new BooksAuthorsDAO();
        AuthorsDAO authorsDAO = new AuthorsDAO();
        try {
            booksDAO.add(books);
            System.out.println("New book is created");
            books = booksDAO.getByName(books.getName());
            if (authorsOfBookList.size() != 0) {
                for (Authors a : authorsOfBookList) {
                    if (authorsDAO.getByFirstnameAndLastname(a).getId() == null) {
                        authorsDAO.add(a);
                        System.out.println("New Author is created");
                        a = authorsDAO.getByFirstnameAndLastname(a);
                    }

                    BooksAuthors booksAuthors = new BooksAuthors();
                    booksAuthors.setBooksId(books.getId());
                    booksAuthors.setAuthorsId(a.getId());

                    booksAuthorsDAO.add(booksAuthors);
                    System.out.println("new books_authors is created");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    //получение сущности книги по ID
    public Books getBooksByID(Long id){
        logger.info("Get book by ID");
        BooksDAO booksDAO = new BooksDAO();
        Books books = new Books();
        try {
            books = booksDAO.getByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
        if (books.getId() == null){
            System.out.println("Book does not exist");
            return null;
        } else {
            return books;
        }
    }

    //Получение сущности книги по имени
    public Books getBooksByName(String bookName){
        logger.info("Get book by name");
        BooksDAO booksDAO = new BooksDAO();
        Books books = new Books();
        try {
            books = booksDAO.getByName(bookName);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
        if (books.getId() == null){
            System.out.println("Book does not exist");
            return null;
        } else {
            return books;
        }
    }

    /*
    Удаление книги:
    Удаление книги изи БД;
    Удаление записей из связующей таблицы из бд
     */
    public void removeBook(Books books){
        logger.info("Delete book");
        BooksDAO booksDAO = new BooksDAO();
        BooksAuthorsDAO booksAuthorsDAO = new BooksAuthorsDAO();
        AccountingRecordsDAO accountingRecordsDAO = new AccountingRecordsDAO();
        try {
            if (booksDAO.getByID(books.getId()).getId() != null){
                if (booksAuthorsDAO.getAllByBookID(books.getId()).size() != 0) {
                    booksAuthorsDAO.removeByBookID(books.getId());
                }
                for (AccountingRecords ar : accountingRecordsDAO.getAllByAccountID(books.getId())){
                    accountingRecordsDAO.remove(ar);
                }
                booksDAO.remove(books);
                System.out.println("Removing is completed");
            } else{
                System.out.println("Book does not exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    //Обновление информации о книге
    public void updateBook(Books books){
        logger.info("Update book");
        BooksDAO booksDAO = new BooksDAO();
        try {
            if (booksDAO.getByID(books.getId()).getId() != null){
                booksDAO.update(books);
                System.out.println("Updating is completed");
            } else{
                System.out.println("Book does not exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    private static void printWithAuthor(Books books) {
        if (books != null) {
            BooksAuthorsDAO booksAuthorsDAO = new BooksAuthorsDAO();
            try {
                List<BooksAuthors> booksAuthorsList = booksAuthorsDAO.getAllByBookID(books.getId());
                if (booksAuthorsList.size() == 0) {
                    System.out.println("\tList is empty");
                    System.out.println();
                } else {
                    System.out.println("\t" + books);
                    System.out.println("\tAuthors of " + books.getName() + " :");
                    AuthorsDAO authorsDAO = new AuthorsDAO();
                    for (BooksAuthors b : booksAuthorsList) {
                        System.out.println("\t" + authorsDAO.getByID(b.getAuthorsId()));
                    }
                    System.out.println();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error(e);
            }
        }
    }

    public void printBook(Books books){
        logger.info("Print book with Author");
        printWithAuthor(books);
    }

    public void printAllBooksByAuthor(Authors authors){
        logger.info("Print all books by author");
        BooksDAO booksDAO = new BooksDAO();
        AuthorsDAO authorsDAO = new AuthorsDAO();
        BooksAuthorsDAO booksAuthorsDAO = new BooksAuthorsDAO();

        System.out.println("All books by " + authors.getFirstname() +
                           " " + authors.getLastname());
        try {
            List<BooksAuthors> booksAuthorsList = booksAuthorsDAO.getAllByAuthorID(authors.getId());
            if (booksAuthorsList.size() == 0){
                System.out.println("empty");
            } else {
                for (BooksAuthors a : booksAuthorsList) {
                    printWithAuthor(booksDAO.getByID(a.getBooksId()));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
            logger.error(e);
        }
    }

    //вывод на экран всех книг и авторов каждой книги
    public void showAllBooks(){
        logger.info("Show all books");
        BooksDAO booksDAO = new BooksDAO();
        try {
            List<Books> booksList = booksDAO.getAll();
            System.out.println("List of Books:");
            if (booksList.size() == 0){
                System.out.println("List is empty");
            } else {
                for (Books a : booksList) {
                    System.out.println("\t" + a);

                    System.out.println("\tAuthors of " + a.getName() + " :");
                    BooksAuthorsDAO booksAuthorsDAO = new BooksAuthorsDAO();
                    List<BooksAuthors> booksAuthorsList = booksAuthorsDAO.getAllByBookID(a.getId());
                    if (booksAuthorsList.size() == 0) {
                        System.out.println("\tList is empty");
                        System.out.println();
                    } else {
                        AuthorsDAO authorsDAO = new AuthorsDAO();
                        for (BooksAuthors b : booksAuthorsList) {
                            System.out.println("\t" + authorsDAO.getByID(b.getAuthorsId()));
                        }
                        System.out.println();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }
}
