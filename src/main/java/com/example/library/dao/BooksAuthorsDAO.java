package com.example.library.dao;

import com.example.library.bl.Util;
import com.example.library.entity.BooksAuthors;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class BooksAuthorsDAO extends Util implements DAO<BooksAuthors, Long>{

    @Override
    public void add(BooksAuthors booksAuthors) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO books_authors (booksid, authorsid) VALUES(?, ?)";
        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, booksAuthors.getBooksId());
            preparedStatement.setLong(2, booksAuthors.getAuthorsId());

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
    public List<BooksAuthors> getAll() throws SQLException {
        Connection connection = getConnection();

        List<BooksAuthors> booksAuthorsList = new ArrayList<>();

        String sql = "SELECT * FROM books_authors";

        Statement statement = null;
        try{
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                BooksAuthors booksAuthors = new BooksAuthors();
                booksAuthors.setBooksId(resultSet.getLong("booksid"));
                booksAuthors.setAuthorsId(resultSet.getLong("authorsid"));

                booksAuthorsList.add(booksAuthors);
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
        return booksAuthorsList;
    }

    @Override
    public BooksAuthors getByID(Long id) throws SQLException {
        return null;
    }

    public List<BooksAuthors> getAllByBookID(Long id) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM books_authors WHERE booksid=?";

        List<BooksAuthors> booksAuthorsList = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BooksAuthors booksAuthors = new BooksAuthors();
                booksAuthors.setBooksId(resultSet.getLong("booksid"));
                booksAuthors.setAuthorsId(resultSet.getLong("authorsid"));

                booksAuthorsList.add(booksAuthors);
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
        return booksAuthorsList;
    }

    public List<BooksAuthors> getAllByAuthorID(Long id) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM books_authors WHERE authorsid=?";

        List<BooksAuthors> booksAuthorsList = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BooksAuthors booksAuthors = new BooksAuthors();
                booksAuthors.setBooksId(resultSet.getLong("booksid"));
                booksAuthors.setAuthorsId(resultSet.getLong("authorsid"));

                booksAuthorsList.add(booksAuthors);
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
        return booksAuthorsList;
    }

    @Override
    public void update(BooksAuthors booksAuthors) throws SQLException {

    }

    @Override
    public void remove(BooksAuthors booksAuthors) throws SQLException {

    }

    public void removeByBookID(Long id) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM books_authors WHERE booksid=?";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

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

    public void removeByAuthorID(Long id) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM clients WHERE authorsid=?";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

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
