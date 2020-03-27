package com.example.library.dao;

import com.example.library.bl.Util;
import com.example.library.entity.AccountingRecords;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

@NoArgsConstructor
public class AccountingRecordsDAO extends Util implements DAO<AccountingRecords, Long> {
    private static final Logger logger = Logger.getLogger(AccountingRecordsDAO.class);

    //добавление
    @Override
    public void add(AccountingRecords accountingRecords) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO accountingrecords (accountid, bookid, receiptdate, returndate, status)" +
                     "VALUES(?, ?, ?, ?, ?)";
        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, accountingRecords.getAccountId());
            preparedStatement.setLong(2, accountingRecords.getBookId());
            preparedStatement.setDate(3, accountingRecords.getReceiptDate());
            preparedStatement.setDate(4, accountingRecords.getReturnDate());
            preparedStatement.setString(5, accountingRecords.getStatus());

            preparedStatement.executeUpdate();
            logger.info("Add accountingRecord to DB");
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

    //получение листа всех записей
    @Override
    public List<AccountingRecords> getAll() throws SQLException {
        Connection connection = getConnection();

        List<AccountingRecords> accountingRecordsList = new ArrayList<>();

        String sql = "SELECT * FROM accountingrecords ORDER BY id";

        Statement statement = null;
        try{
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                AccountingRecords accountingRecords = new AccountingRecords();
                accountingRecords.setId(resultSet.getLong("id"));
                accountingRecords.setAccountId(resultSet.getLong("accountid"));
                accountingRecords.setBookId(resultSet.getLong("bookid"));
                accountingRecords.setReceiptDate(resultSet.getDate("receiptdate"));
                accountingRecords.setReturnDate(resultSet.getDate("returndate"));
                accountingRecords.setStatus(resultSet.getString("status"));

                accountingRecordsList.add(accountingRecords);
            }
            logger.info("Get list of accountingRecords from DB");
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
        return accountingRecordsList;
    }

    //получение записи по ID
    @Override
    public AccountingRecords getByID(Long id) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        if (id == null){
            id = 0L;
        }

        String sql = "SELECT * FROM accountingrecords WHERE id=?";

        AccountingRecords accountingRecords = new AccountingRecords();
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                accountingRecords.setId(resultSet.getLong("id"));
                accountingRecords.setAccountId(resultSet.getLong("accountid"));
                accountingRecords.setBookId(resultSet.getLong("bookid"));
                accountingRecords.setReceiptDate(resultSet.getDate("receiptdate"));
                accountingRecords.setReturnDate(resultSet.getDate("returndate"));
                accountingRecords.setStatus(resultSet.getString("status"));
            }
            logger.info("Get accountingRecord by ID from DB");
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
        return accountingRecords;
    }

    //получение листа записей по ID клиента
    public List<AccountingRecords> getAllByAccountID(Long id) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        if (id == null){
            id = 0L;
        }

        String sql = "SELECT * FROM accountingrecords WHERE accountid=?";

        List<AccountingRecords> accountingRecordsList = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AccountingRecords accountingRecords = new AccountingRecords();
                accountingRecords.setId(resultSet.getLong("id"));
                accountingRecords.setAccountId(resultSet.getLong("accountid"));
                accountingRecords.setBookId(resultSet.getLong("bookid"));
                accountingRecords.setReceiptDate(resultSet.getDate("receiptdate"));
                accountingRecords.setReturnDate(resultSet.getDate("returndate"));
                accountingRecords.setStatus(resultSet.getString("status"));

                accountingRecordsList.add(accountingRecords);
            }
            logger.info("Get list of accountingRecords by clientID from DB");
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
        return accountingRecordsList;
    }

    //получение листа записей по ID книги
    public List<AccountingRecords> getAllByBookID(Long id) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;
        if (id == null){
            id = 0L;
        }

        String sql = "SELECT * FROM accountingrecords WHERE bookid=?";

        List<AccountingRecords> accountingRecordsList = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AccountingRecords accountingRecords = new AccountingRecords();
                accountingRecords.setId(resultSet.getLong("id"));
                accountingRecords.setAccountId(resultSet.getLong("accountid"));
                accountingRecords.setBookId(resultSet.getLong("bookid"));
                accountingRecords.setReceiptDate(resultSet.getDate("receiptdate"));
                accountingRecords.setReturnDate(resultSet.getDate("returndate"));
                accountingRecords.setStatus(resultSet.getString("status"));

                accountingRecordsList.add(accountingRecords);
            }
            logger.info("Get list of accountingRecords by bookID from DB");
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
        return accountingRecordsList;
    }

    //изменение
    @Override
    public void update(AccountingRecords accountingRecords) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "UPDATE accountingrecords SET accountid=?, bookid=?, receiptdate=?," +
                     "returndate=?, status=? WHERE id=?";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, accountingRecords.getAccountId());
            preparedStatement.setLong(2, accountingRecords.getBookId());
            preparedStatement.setDate(3, accountingRecords.getReceiptDate());
            preparedStatement.setDate(4, accountingRecords.getReturnDate());
            preparedStatement.setString(5,accountingRecords.getStatus());
            preparedStatement.setLong(6, accountingRecords.getId());

            preparedStatement.executeUpdate();

            logger.info("Update accountingRecord from DB");
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

    //удаление
    @Override
    public void remove(AccountingRecords accountingRecords) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM accountingrecords WHERE id=?";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, accountingRecords.getId());

            preparedStatement.executeUpdate();

            logger.info("Delete accountingRecord from DB");

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
