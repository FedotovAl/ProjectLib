package com.example.library.dao;

import com.example.library.bl.Util;
import com.example.library.entity.BooksAuthors;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

@NoArgsConstructor
public class BooksAuthorsDAO extends Util implements DAO<BooksAuthors, Long>{
    private static final Logger logger = Logger.getLogger(BooksAuthorsDAO.class);

    //добавление записи в промежуточную таблицу
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

            logger.info("Add booksAuthors record to DB");
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

    //получение листа записей из промежуточных таблиц
    @Override
    public List<BooksAuthors> getAll() throws SQLException {
        Connection connection = getConnection();

        List<BooksAuthors> booksAuthorsList = new ArrayList<>();

        String sql = "SELECT * FROM books_authors ORDER BY booksid";

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
            logger.info("Get list of booksAuthors records from DB");
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
        return booksAuthorsList;
    }

    @Override
    public BooksAuthors getByID(Long id) throws SQLException {
        return null;
    }

    //получение листа всех записей по ID книги
    public List<BooksAuthors> getAllByBookID(Long id) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        if (id == null){
            id = 0L;
        }

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
            logger.info("Get list of booksAuthors records by bookID from DB");
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
        return booksAuthorsList;
    }

    //Получение листа всех записей по ID автора
    public List<BooksAuthors> getAllByAuthorID(Long id) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        if (id == null){
            id = 0L;
        }

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
            logger.info("Get list of booksAuthors records by authorID from DB");
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
        return booksAuthorsList;
    }

    @Override
    public void update(BooksAuthors booksAuthors) throws SQLException {

    }

    @Override
    public void remove(BooksAuthors booksAuthors) throws SQLException {

    }

    //удаление запесей, содержащих ID книги
    public void removeByBookID(Long id) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        if (id == null){
            id = 0L;
        }

        String sql = "DELETE FROM books_authors WHERE booksid=?";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            logger.info("Delete booksAuthors record by bookID from DB");
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

    //удаление записей по ID автора
    public void removeByAuthorID(Long id) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        if (id == null){
            id = 0L;
        }

        String sql = "DELETE FROM clients WHERE authorsid=?";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            logger.info("Delete booksAuthors record by authorID from DB");

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
