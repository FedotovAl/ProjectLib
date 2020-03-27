package com.example.library.view;

import com.example.library.controller.AccountingRecordsController;
import com.example.library.controller.AuthorController;
import com.example.library.controller.BookController;
import com.example.library.controller.ClientController;
import com.example.library.entity.AccountingRecords;
import com.example.library.entity.Authors;
import com.example.library.entity.Books;
import com.example.library.entity.Client;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainView {
    private static Scanner scanner = new Scanner(System.in);

    public static void mainMenu(){
        int peremMenu = -1;
        AccountingRecordsController accountingRecordsController = new AccountingRecordsController();
        while (peremMenu != 0){
            System.out.println();
            System.out.println("=======================================");
            System.out.println("\t  Меню Библиотеки");
            System.out.println("(1) - Редактирование данных");
            System.out.println("(2) - Показать все книги");
            System.out.println("(3) - Поиск книги");
            System.out.println("(4) - Выдача книги");
            System.out.println("(0) - Выход\n");
            System.out.println("Введите номер пункта меню, для перехода к нему:");

            peremMenu = scanner.nextInt();

            switch (peremMenu){
                case 1:
                    changeDataMain();
                    break;
                case 2:
                    showBooks();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4: {
                    addAccRec();
                }
                    break;
                case 0:
                    System.out.println("Завершение работы программы");
                    break;
                default:
                    System.out.println("Некорректный ввод");
            }
        }



    }

    private static void searchBook(){
        BookController bookController = new BookController();
        AuthorController authorController = new AuthorController();

        int peremSearch = -1;
        while(peremSearch != 0){
            System.out.println();
            System.out.println("=======================================");
            System.out.println("(1) - Поиск книги по ID");
            System.out.println("(2) - Поиск книги по названию");
            System.out.println("(3) - Поиск книг по автору");
            System.out.println("(0) - Назад");
            peremSearch = scanner.nextInt();
            switch (peremSearch){
                case 1: {
                    System.out.println("Введите ID книги");
                    Long id = scanner.nextLong();
                    bookController.printBook(bookController.getBookByID(id));
                }
                    break;
                case 2: {
                    System.out.println("Введите название книги");
                    String bookName = null;
                    scanner.nextLine();
                    bookName = scanner.nextLine();
                    bookController.printBook(bookController.getBookByName(bookName));
                }
                    break;
                case 3: {
                    authorController.showAllAuthors();
                    System.out.println("Введите ID автора");
                    Long id = scanner.nextLong();
                    bookController.printAllBooksByAuthor(authorController.getAuthorByID(id));
                }
                    break;
                    case 0:
                        System.out.println("Выход из \"Поиск книги\"\n");
                    break;
                default:
                    System.out.println("Некорректный ввод");
            }
        }
    }

    private static void showBooks(){
        BookController bookController = new BookController();

        int peremShow = -1;
        while (peremShow != 0){
            System.out.println();
            bookController.showAllBooks();
            System.out.println("=======================================");
            System.out.println("(0) - Назад");
            peremShow = scanner.nextInt();
            switch (peremShow){
                case 0:
                    System.out.println("Выход из \"Показать все книги\"\n");
                    break;
                default:
                    System.out.println("Некорректный ввод");
            }
        }
    }

    private static void addAccRec(){
        AccountingRecordsController accountingRecordsController = new AccountingRecordsController();
        ClientController clientController = new ClientController();
        BookController bookController = new BookController();
        accountingRecordsController.checkExpireStatus();
        int peremAddAR = -1;
        while (peremAddAR != 0){
            System.out.println();
            System.out.println("=======================================");
            System.out.println("(1) - Выдать книгу");
            System.out.println("(0) - Назад");
            peremAddAR = scanner.nextInt();
            switch (peremAddAR){
                case 1: {
                    AccountingRecords accountingRecords = new AccountingRecords();
                    clientController.showAllClients();
                    System.out.println("Введите ID клиента, которому выдается книга");
                    Long idClient = scanner.nextLong();
                    accountingRecords.setAccountId(idClient);
                    bookController.showAllBooks();
                    System.out.println("Введите название книги");
                    String bookName = null;
                    scanner.nextLine();
                    bookName = scanner.nextLine();
                    System.out.println("На сколько дней выдается книга?");
                    int day = scanner.nextInt();
                    LocalDate dateNow = LocalDate.now();
                    accountingRecords.setReceiptDate(Date.valueOf(dateNow));
                    accountingRecords.setReturnDate(Date.valueOf(dateNow.plusDays(day)));
                    accountingRecords.setStatus("opened");
                    accountingRecordsController.addNewAccountingRecord(accountingRecords, bookName);
                }
                    break;
                case 0:
                    System.out.println("Выход из \"Выдача книги\"\n");
                    break;
                default:
                    System.out.println("Некорректный ввод");
            }
        }
    }




    private static void changeDataMain(){
        int peremCh = -1;
        while(peremCh != 0){
            System.out.println();
            System.out.println("=======================================");
            System.out.println("(1) - Редактировать книги");
            System.out.println("(2) - Редактировать авторов книг");
            System.out.println("(3) - Редактировать клиентов");
            System.out.println("(4) - Редактировать учетные записи");
            System.out.println("(0) - Назад");
            peremCh = scanner.nextInt();
            switch (peremCh){
                case 1:
                    changeDataBook();
                    break;
                case 2:
                    changeDataAuthor();
                    break;
                case 3:
                    changeDataClient();
                    break;
                case 4:
                    changeDataAR();
                    break;
                case 0:
                    System.out.println("Выход из \"Редактирование данных\"\n");
                    break;
                default:
                    System.out.println("Некорректный ввод");
            }
        }
    }

    private static void changeDataBook(){
        BookController bookController = new BookController();
        int peremDBook = -1;
        while(peremDBook != 0){
            System.out.println();
            bookController.showAllBooks();
            System.out.println("=======================================");
            System.out.println("(1) - Удалить");
            System.out.println("(2) - Изменить");
            System.out.println("(3) - Добавить");
            System.out.println("(0) - Назад");
            peremDBook = scanner.nextInt();
            switch (peremDBook){
                case 1:{
                    bookController.showAllBooks();
                    System.out.println("Введите id книги, которую хотите удалить");
                    Long id = scanner.nextLong();
                    if (bookController.getBookByID(id) != null) {
                        bookController.removeBook(id);
                    } else {
                        System.out.println("Книги с таким ID нет");
                    }
                }
                    break;
                case 2:{
                    bookController.showAllBooks();
                    System.out.println("Введите id книги, которую хотите изменить");
                    Long id = scanner.nextLong();
                    Books book = bookController.getBookByID(id);
                    if (book != null) {
                        System.out.println("Введите новое название книги");
                        String bookName = null;
                        scanner.nextLine();
                        bookName = scanner.nextLine();
                        book.setName(bookName);
                        System.out.println("Введите новую категорию книги");
                        String categoryName = null;
                        categoryName = scanner.nextLine();
                        book.setCategory(categoryName);
                        bookController.updateBook(book);
                    } else {
                        System.out.println("Книги с таким ID нет");
                    }
                }
                    break;
                case 3:{
                    Books book = new Books();
                    System.out.println("Введите название книги");
                    String bookName = null;
                    scanner.nextLine();
                    bookName = scanner.nextLine();
                    book.setName(bookName);
                    System.out.println("Введите категорию книги");
                    String categoryName = null;
                    categoryName = scanner.nextLine();
                    book.setCategory(categoryName);
                    List<Authors> authorsList = new ArrayList<>();
                    int add = -1;
                    while (add != 0){
                        System.out.println();
                        System.out.println("Добавить автора книги?");
                        System.out.println("(1) - Да");
                        System.out.println("(0) - Нет");
                        add = scanner.nextInt();
                        if (add != 0){
                            Authors authors = new Authors();
                            System.out.println("Укажите фамилию автора");
                            String lastname = null;
                            scanner.nextLine();
                            lastname = scanner.nextLine();
                            authors.setLastname(lastname);
                            System.out.println("Укажите имя автора");
                            String firstname = null;
                            firstname = scanner.nextLine();
                            authors.setFirstname(firstname);
                            authorsList.add(authors);
                        }
                    }
                    //System.out.println(book);
                    //for (Authors au : authorsList){
                    //    System.out.println(au);
                    //}
                    bookController.addNewBook(book, authorsList);
                }
                    break;
                case 0:
                    System.out.println("Выход из \"Редактировать книги\"\n");
                    break;
                default:
                    System.out.println("Некорректный ввод");
            }
        }
    }

    private static void changeDataAuthor(){
        AuthorController authorController = new AuthorController();
        int peremDAuthor = -1;
        while(peremDAuthor != 0){
            System.out.println();
            authorController.showAllAuthors();
            System.out.println("=======================================");
            System.out.println("(1) - Удалить");
            System.out.println("(2) - Изменить");
            System.out.println("(3) - Добавить");
            System.out.println("(0) - Назад");
            peremDAuthor = scanner.nextInt();
            switch (peremDAuthor){
                case 1:{
                    authorController.showAllAuthors();
                    System.out.println("Введите id автора, которого хотите удалить");
                    Long id = scanner.nextLong();
                    if (authorController.getAuthorByID(id) != null) {
                        authorController.removeAuthor(id);
                    }
                }
                break;
                case 2:{
                    authorController.showAllAuthors();
                    System.out.println("Введите id автора, которого хотите переименовать");
                    Long id = scanner.nextLong();
                    Authors authors = authorController.getAuthorByID(id);
                    if (authors != null) {
                        System.out.println("Введите новую фамилию автора");
                        String lastname = null;
                        scanner.nextLine();
                        lastname = scanner.nextLine();
                        authors.setLastname(lastname);
                        System.out.println("Введите новое имя автора");
                        String firstname = null;
                        firstname = scanner.nextLine();
                        authors.setFirstname(firstname);
                        authorController.updateAuthor(authors);
                    }
                }
                break;
                case 3:{
                    Authors authors = new Authors();
                    System.out.println("Введите фамилию автора");
                    String lastname = null;
                    scanner.nextLine();
                    lastname = scanner.nextLine();
                    authors.setLastname(lastname);
                    System.out.println("Введите имя автора");
                    String firstname = null;
                    firstname = scanner.nextLine();
                    authors.setFirstname(firstname);
                    authorController.addNewAuthor(authors);
                }
                break;
                case 0:
                    System.out.println("Выход из \"Редактировать автора\"\n");
                    break;
                default:
                    System.out.println("Некорректный ввод");
            }
        }
    }

    private static void changeDataAR(){
        AccountingRecordsController accountingRecordsController = new AccountingRecordsController();
        int peremDAR = -1;
        while (peremDAR != 0){
            System.out.println();
            accountingRecordsController.showAllAccountingRecords();
            System.out.println("=======================================");
            System.out.println("(1) - Закрыть учетную запись");
            System.out.println("(0) - Назад");
            peremDAR = scanner.nextInt();
            switch (peremDAR){
                case 1:{
                    accountingRecordsController.showAllAROpen();
                    System.out.println("Введите id учетной записи, которую хотите закрыть");
                    Long idAR = scanner.nextLong();
                    accountingRecordsController.closeStatus(idAR);
                }
                    break;
                case 0:
                    System.out.println("Выход из \"Редактировать учетную запись\"\n");
                    break;
                default:
                    System.out.println("Некорректный ввод");
            }
        }
    }

    private static void changeDataClient(){
        ClientController clientController = new ClientController();
        int peremDClient = -1;
        while(peremDClient != 0){
            System.out.println();
            clientController.showAllClients();
            System.out.println("=======================================");
            System.out.println("(1) - Удалить");
            System.out.println("(2) - Изменить");
            System.out.println("(3) - Добавить");
            System.out.println("(0) - Назад");
            peremDClient = scanner.nextInt();
            switch (peremDClient){
                case 1:{
                    clientController.showAllClients();
                    System.out.println("Введите id клиента, которого хотите удалить");
                    Long id = scanner.nextLong();
                    if (clientController.getClientByID(id) != null) {
                        clientController.removeClientByID(id);
                    }
                }
                break;
                case 2:{
                    clientController.showAllClients();
                    System.out.println("Введите id клиента, которого хотите переименовать");
                    Long id = scanner.nextLong();
                    Client client = clientController.getClientByID(id);
                    if (client != null) {
                        System.out.println("Введите новую фамилию клиента");
                        String lastname = null;
                        scanner.nextLine();
                        lastname = scanner.nextLine();
                        client.setLastname(lastname);
                        System.out.println("Введите новое имя клиента");
                        String firstname = null;
                        firstname = scanner.nextLine();
                        client.setFirstname(firstname);
                        clientController.updateClient(client);
                    }
                }
                break;
                case 3:{
                    Client client = new Client();
                    System.out.println("Введите фамилию клиента");
                    String lastname = null;
                    scanner.nextLine();
                    lastname = scanner.nextLine();
                    client.setLastname(lastname);
                    System.out.println("Введите имя клиента");
                    String firstname = null;
                    firstname = scanner.nextLine();
                    client.setFirstname(firstname);
                    clientController.addNewClient(client);
                }
                break;
                case 0:
                    System.out.println("Выход из \"Редактировать клиента\"\n");
                    break;
                default:
                    System.out.println("Некорректный ввод");
            }
        }
    }
}
