package com.example.library.dao;

import com.example.library.bl.Util;
import com.example.library.entity.Authors;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class AuthorsDAO extends Util implements DAO<Authors, Long> {
    @Override
    public void add(Authors authors) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO authors (firstname, lastname) VALUES(?, ?)";
        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, authors.getFirstname());
            preparedStatement.setString(2, authors.getLastname());

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
    public List<Authors> getAll() throws SQLException {
        Connection connection = getConnection();

        List<Authors> authorsList = new ArrayList<>();

        String sql = "SELECT * FROM authors";

        Statement statement = null;
        try{
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Authors authors = new Authors();
                authors.setId(resultSet.getLong("id"));
                authors.setFirstname(resultSet.getString("firstname"));
                authors.setLastname(resultSet.getString("lastname"));

                authorsList.add(authors);
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
        return authorsList;
    }

    @Override
    public Authors getByID(Long id) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM authors WHERE id=?";

        Authors authors = new Authors();
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                authors.setId(resultSet.getLong("id"));
                authors.setFirstname(resultSet.getString("firstname"));
                authors.setLastname(resultSet.getString("lastname"));
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
        return authors;
    }

    @Override
    public void update(Authors authors) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "UPDATE authors SET firstname=?, lastname=? WHERE id=?";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, authors.getFirstname());
            preparedStatement.setString(2, authors.getLastname());
            preparedStatement.setLong(3, authors.getId());

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
    public void remove(Authors authors) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM authors WHERE id=?";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, authors.getId());

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
