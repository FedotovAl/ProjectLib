package com.example.library.service;

import com.example.library.dao.*;
import com.example.library.entity.AccountingRecords;
import com.example.library.entity.Books;
import com.example.library.entity.Client;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.apache.log4j.Logger;

public class AccountingRecordsService {
    private static final Logger logger = Logger.getLogger(AccountingRecordsService.class);

    //Проверка корректности при создании учетной записи
    private static boolean isCorrect(AccountingRecords accountingRecords){
        AccountingRecordsDAO accountingRecordsDAO = new AccountingRecordsDAO();
        BooksDAO booksDAO = new BooksDAO();
        ClientDAO clientDAO = new ClientDAO();
        boolean canToCreate = true;
        try {
            if (clientDAO.getByID(accountingRecords.getAccountId()).getId() == null) {
                System.out.println("user does not exist");
                canToCreate = false;
            }
            if (booksDAO.getByID(accountingRecords.getBookId()).getId() == null) {
                System.out.println("book does not exist");
                canToCreate = false;
            }
            if (accountingRecords.getReceiptDate().compareTo(accountingRecords.getReturnDate()) > 0) {
                System.out.println("receiptDate after returnDate");
                canToCreate = false;
            }

            return canToCreate;
        } catch(SQLException e){
            e.printStackTrace();
            logger.error(e);
        }
        return canToCreate;
    }

    //Проверка, меняющая автоматически статусы всех просроченных записей
    public void expireStatus(){
        logger.info("Check all records status");
        int countOfChanges = 0;
        AccountingRecordsDAO accountingRecordsDAO = new AccountingRecordsDAO();
        try {
            List<AccountingRecords> accountingRecordsList = accountingRecordsDAO.getAll();
            LocalDate today = LocalDate.now();
            Date date = Date.valueOf(today);
            for (AccountingRecords a : accountingRecordsList){
                if (date.compareTo(a.getReturnDate()) > 0 && a.getStatus().equals("opened")){
                    a.setStatus("expired");
                    accountingRecordsDAO.update(a);
                    countOfChanges++;
                }
            }
            System.out.println("Today was expired " + countOfChanges + " records");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    /*
    Добавление учетной записи с проверкой на корректнсть данных
    и на доступность книги, которую собираются выдать
     */
    public void addNewAccountingRecord(AccountingRecords accountingRecords, String bookName){
        logger.info("Add new accounting record");
        AccountingRecordsDAO accountingRecordsDAO = new AccountingRecordsDAO();
        BooksDAO booksDAO = new BooksDAO();
        try {
            accountingRecords.setBookId(booksDAO.getByName(bookName).getId());
            boolean canToCreate = true;
            List<AccountingRecords> accountingRecordsList;
            accountingRecordsList = accountingRecordsDAO.getAllByBookID(accountingRecords.getBookId());
            for (AccountingRecords a : accountingRecordsList) {
                if (a.getStatus().equals("opened") || a.getStatus().equals("expired")) {
                    System.out.println("Book was given");
                    canToCreate = false;
                    break;
                }
            }
            if(isCorrect(accountingRecords) && canToCreate) {
                accountingRecordsDAO.add(accountingRecords);
                System.out.println("New accounting record is created");
            }
        } catch(SQLException e){
            e.printStackTrace();
            logger.error(e);
        }
    }

    //получение сущности учетной записи по ID
    public AccountingRecords getAccountingRecordByID(Long id){
        logger.info("Get accounting record by ID");
        AccountingRecordsDAO accountingRecordsDAO = new AccountingRecordsDAO();
        AccountingRecords accountingRecords = new AccountingRecords();
        try {
            accountingRecords = accountingRecordsDAO.getByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
        if (accountingRecords.getId() == null){
            System.out.println("Accounting record does not exist");
            return null;
        } else {
            return accountingRecords;
        }
    }

    //удаление учетной записи
    public void removeAccountingRecord(AccountingRecords accountingRecords){
        logger.info("Delete accounting record");
        AccountingRecordsDAO accountingRecordsDAO = new AccountingRecordsDAO();
        try {
            if (accountingRecordsDAO.getByID(accountingRecords.getId()).getId() != null){
                accountingRecordsDAO.remove(accountingRecords);
                System.out.println("Removing is comleted");
            } else{
                System.out.println("Record does not exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    //изменение учетной записи
    public void updateAccountingRecord(AccountingRecords accountingRecords){
        logger.info("Update accounting record");
        AccountingRecordsDAO accountingRecordsDAO = new AccountingRecordsDAO();
        try {
            if (accountingRecordsDAO.getByID(accountingRecords.getId()).getId() != null &&
                isCorrect(accountingRecords)){
                accountingRecordsDAO.update(accountingRecords);
                System.out.println("Updating is completed");
            } else{
                System.out.println("Record does not exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    //изменение статуса учетной записи на "закрыто"
    public void closeStatus(AccountingRecords accountingRecords){
        logger.info("Close accounting records status");
        if (accountingRecords != null) {
            AccountingRecordsDAO accountingRecordsDAO = new AccountingRecordsDAO();
            try {
                if (accountingRecordsDAO.getByID(accountingRecords.getId()).getId() != null &&
                        isCorrect(accountingRecords)) {
                    accountingRecords.setStatus("closed");
                    accountingRecordsDAO.update(accountingRecords);
                    System.out.println("endStatus is completed");
                } else {
                    System.out.println("Record does not exist");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error(e);
            }
        } else {
            System.out.println("Accounting record does not exist");
        }
    }

    /*
    Вывод листа всех учетных записей с названием книги и данными о клиенте
    В случае, если клиент или книга были удалена, то выводится
    соответствующая информация об этом
     */
    public void showAllAccountingRecords(){
        logger.info("Show all accounting records");
        AccountingRecordsDAO accountingRecordsDAO = new AccountingRecordsDAO();
        ClientDAO clientDAO = new ClientDAO();
        BooksDAO booksDAO = new BooksDAO();
        List<AccountingRecords> accountingRecordsList;
        try{
            accountingRecordsList = accountingRecordsDAO.getAll();
            if (accountingRecordsList.size() == 0){
                System.out.println("List is empty");
            } else {
                Client client;
                Books books;
                for (AccountingRecords a : accountingRecordsList) {
                    System.out.println("List of AccountingRecords:");
                    System.out.println("\t" + a);
                    client = clientDAO.getByID(a.getAccountId());
                    if (client.getId() == null){
                        System.out.println("\tClient was deleted");
                    } else {
                        System.out.println("\tName of client " + client.getFirstname() +
                                " " + client.getLastname());
                    }
                    books = booksDAO.getByID(a.getBookId());
                    if (books.getId() == null){
                        System.out.println("\tBook was deleted");
                    } else {
                        System.out.println("\tName of book " + books.getName());
                    }
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    public void showAllAccountingRecordsWhichOpen(){
        logger.info("Show all accounting records which open");
        AccountingRecordsDAO accountingRecordsDAO = new AccountingRecordsDAO();
        ClientDAO clientDAO = new ClientDAO();
        BooksDAO booksDAO = new BooksDAO();
        List<AccountingRecords> accountingRecordsList;
        try{
            accountingRecordsList = accountingRecordsDAO.getAll();
            if (accountingRecordsList.size() == 0){
                System.out.println("List is empty");
            } else {
                Client client;
                Books books;
                for (AccountingRecords a : accountingRecordsList) {
                    if (a.getStatus().equals("opened") || a.getStatus().equals("expired")) {
                        System.out.println("List of AccountingRecords:");
                        System.out.println("\t" + a);
                        client = clientDAO.getByID(a.getAccountId());
                        if (client.getId() == null || client.getId() == 0) {
                            System.out.println("\tClient was deleted");
                        } else {
                            System.out.println("\tName of client " + client.getFirstname() +
                                    " " + client.getLastname());
                        }
                        books = booksDAO.getByID(a.getBookId());
                        if (books.getId() == null || books.getId() == 0) {
                            System.out.println("\tBook was deleted");
                        } else {
                            System.out.println("\tName of book " + books.getName());
                        }
                    }
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }
}
