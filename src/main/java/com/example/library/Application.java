package com.example.library;

import com.example.library.dao.AccountingRecordsDAO;
import com.example.library.dao.AuthorsDAO;
import com.example.library.dao.BooksDAO;
import com.example.library.dao.ClientDAO;
import com.example.library.entity.AccountingRecords;
import com.example.library.entity.Authors;
import com.example.library.entity.Books;
import com.example.library.entity.Client;
import com.example.library.service.AccountingRecordsService;
import com.example.library.service.AuthorsService;
import com.example.library.service.BooksService;
import com.example.library.service.ClientService;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        BooksService booksService = new BooksService();
        AuthorsService authorsService = new AuthorsService();
        AccountingRecordsService accountingRecordsService = new AccountingRecordsService();

        //TEST

// ClientServiceTest TEST
//        Client client = new Client();
//        client.setFirstname("Алексей");
//        client.setLastname("Алексеев");
//        clientService.addNewClient(client);
//        int x = 4;
//        Long l = Long.valueOf(x);
//        client.setId(l);
//        clientService.removeClient(client);
//        client.setId(3L);
//        client.setFirstname("Мария");
//        client.setLastname("Петрова");
//        clientService.updateClient(client);
//        clientService.showAllClients();

        //AuthorsService and BooksService TEST
//        List<Authors> authorsList = new ArrayList<>();
//        Authors authors1 = new Authors();
//        authors1.setFirstname("Борис");
//        authors1.setLastname("Стругацкий");
//        authorsList.add(authors1);
//        Authors authors2 = new Authors();
//        authors2.setFirstname("Аркадий");
//        authors2.setLastname("Стругацкий");
//        authorsList.add(authors2);
////
//        Books books = new Books();
//        books.setName("Трудно быть богом");
//        books.setCategory("Фантастика");
//        books.setId(6L);
//        booksService.updateBook(books);
//        booksService.showAllBooks();

        //AccountingRecordsService TEST
//        AccountingRecords accountingRecords = new AccountingRecords();
////        AccountingRecordsDAO accountingRecordsDAO = new AccountingRecordsDAO();
////        try {
////            accountingRecords = accountingRecordsDAO.getByID(1L);
////            accountingRecords.setReceiptDate(Date.valueOf("2020-03-10"));
////            accountingRecords.setReturnDate(Date.valueOf("2020-03-15"));
////            //accountingRecords.setStatus("opened");
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }


//        LocalDate today = LocalDate.now();
//        Date date = Date.valueOf(today);
//        System.out.println(date);
//        accountingRecordsService.updateAccountingRecord(accountingRecords)

//
//        accountingRecordsService.showAllAccountingRecords();
//        accountingRecordsService.expireStatus();
//        accountingRecordsService.showAllAccountingRecords();
//        ClientService clientService1 = new ClientService();
//        Client client = clientService.getClientByID(5L);
//        System.out.println(client);

        clientService.showAllClients();




    }
}
