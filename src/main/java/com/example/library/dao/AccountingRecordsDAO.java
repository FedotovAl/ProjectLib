package com.example.library.dao;

import com.example.library.bl.Util;
import com.example.library.entity.AccountingRecords;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class AccountingRecordsDAO extends Util implements DAO<AccountingRecords, Long> {
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
    public List<AccountingRecords> getAll() throws SQLException {
        Connection connection = getConnection();

        List<AccountingRecords> accountingRecordsList = new ArrayList<>();

        String sql = "SELECT * FROM accountingrecords";

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
        return accountingRecordsList;
    }

    @Override
    public AccountingRecords getByID(Long id) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

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
        return accountingRecords;
    }

    public List<AccountingRecords> getAllByAccountID(Long id) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

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
        return accountingRecordsList;
    }

    public List<AccountingRecords> getAllByBookID(Long id) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

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
        return accountingRecordsList;
    }

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
    public void remove(AccountingRecords accountingRecords) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM accountingrecords WHERE id=?";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, accountingRecords.getId());

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
