package com.example.library.dao;

import com.example.library.bl.Util;
import com.example.library.entity.Books;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

@NoArgsConstructor
public class BooksDAO extends Util implements DAO<Books, Long> {
    private static final Logger logger = Logger.getLogger(BooksDAO.class);
    //добавление книги
    @Override
    public void add(Books books) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO books (name, category) VALUES(?, ?)";
        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, books.getName());
            preparedStatement.setString(2, books.getCategory());

            preparedStatement.executeUpdate();

            logger.info("Add book to DB");

        }
        catch (SQLException e){
            e.printStackTrace();
            logger.error(e);
        }
        finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }
    //Получение листа всех книг
    @Override
    public List<Books> getAll() throws SQLException {
        Connection connection = getConnection();

        List<Books> booksList = new ArrayList<>();

        String sql = "SELECT * FROM books ORDER BY id";

        Statement statement = null;
        try{
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Books books = new Books();
                books.setId(resultSet.getLong("id"));
                books.setName(resultSet.getString("name"));
                books.setCategory(resultSet.getString("category"));

                booksList.add(books);
            }
            logger.info("Get list of books from DB");
        }
        catch (SQLException e){
            e.printStackTrace();
            logger.error(e);
        }
        finally {
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
        return booksList;
    }

    //Получение книги по ID
    @Override
    public Books getByID(Long id) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM books WHERE id=?";

        Books books = new Books();
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                books.setId(resultSet.getLong("id"));
                books.setName(resultSet.getString("name"));
                books.setCategory(resultSet.getString("category"));
            }
            logger.info("Get book by ID from DB");
        } catch (SQLException e){
            e.printStackTrace();
            logger.error(e);
        } finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
        return books;
    }

    //Получение книги по названию
    public Books getByName(String name) throws SQLException{
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM books WHERE name=?";

        Books books = new Books();
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                books.setId(resultSet.getLong("id"));
                books.setName(resultSet.getString("name"));
                books.setCategory(resultSet.getString("category"));
            }
            logger.info("Get book by name from DB");
        } catch (SQLException e){
            e.printStackTrace();
            logger.error(e);
        } finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
        return books;
    }

    //Получение листа книг по категории
    public List<Books> getAllByCategory(String category) throws SQLException{
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM books WHERE category=?";

        List<Books> booksList = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, category);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Books books = new Books();
                books.setId(resultSet.getLong("id"));
                books.setName(resultSet.getString("name"));
                books.setCategory(resultSet.getString("category"));

                booksList.add(books);
            }
            logger.info("Get list of books by category from DB");
        } catch (SQLException e){
            e.printStackTrace();
            logger.error(e);
        } finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
        return booksList;
    }

    //обновление книги
    @Override
    public void update(Books books) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "UPDATE books SET name=?, category=? WHERE id=?";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, books.getName());
            preparedStatement.setString(2, books.getCategory());
            preparedStatement.setLong(3, books.getId());

            preparedStatement.executeUpdate();
            logger.info("Update book from DB");
        } catch (SQLException e){
            e.printStackTrace();
            logger.error(e);
        } finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }

    //удаление книги
    @Override
    public void remove(Books books) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM books WHERE id=?";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, books.getId());

            preparedStatement.executeUpdate();

            logger.info("Delete book from DB");

        } catch (SQLException e){
            e.printStackTrace();
            logger.error(e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
