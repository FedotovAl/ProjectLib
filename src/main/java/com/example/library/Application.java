package com.example.library;

import com.example.library.dao.BooksDAO;
import com.example.library.dao.ClientDAO;
import com.example.library.entity.Books;
import com.example.library.entity.Client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        BooksDAO bd = new BooksDAO();
        Books books = new Books();
        List<Books> lb = new ArrayList<>();

        try {
            lb = bd.getByCategory("Роман");
            for (Books a : lb){
                System.out.println(a.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
