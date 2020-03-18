package com.example.library.dao;

import com.example.library.bl.Util;
import com.example.library.entity.Books;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class BooksDAO extends Util implements DAO<Books, Long> {
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
        }
        catch (SQLException e){
            e.printStackTrace();
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

    @Override
    public List<Books> getAll() throws SQLException {
        Connection connection = getConnection();

        List<Books> booksList = new ArrayList<>();

        String sql = "SELECT * FROM books";

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
        }
        catch (SQLException e){
            e.printStackTrace();
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
        } catch (SQLException e){
            e.printStackTrace();
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
        } catch (SQLException e){
            e.printStackTrace();
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
        } catch (SQLException e){
            e.printStackTrace();
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
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }

    @Override
    public void remove(Books books) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM books WHERE id=?";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, books.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
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
