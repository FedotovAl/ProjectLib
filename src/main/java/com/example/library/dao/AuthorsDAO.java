package com.example.library.dao;

import com.example.library.bl.Util;
import com.example.library.entity.Authors;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

@NoArgsConstructor
public class AuthorsDAO extends Util implements DAO<Authors, Long> {
    private static final Logger logger = Logger.getLogger(AuthorsDAO.class);

    //добавление
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

            logger.info("Add author to DB");
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

    //получение листа авторов
    @Override
    public List<Authors> getAll() throws SQLException {
        Connection connection = getConnection();

        List<Authors> authorsList = new ArrayList<>();

        String sql = "SELECT * FROM authors ORDER BY id";

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
            logger.info("Get list of authors from DB");
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
        return authorsList;
    }

    //получение автора по id
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
            logger.info("Get author by ID from DB");
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
        return authors;
    }

    //получение автора по имени и фамилии
    public Authors getByFirstnameAndLastname(Authors authors) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM authors WHERE firstname=? AND lastname=?";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, authors.getFirstname());
            preparedStatement.setString(2,authors.getLastname());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                authors.setId(resultSet.getLong("id"));
                authors.setFirstname(resultSet.getString("firstname"));
                authors.setLastname(resultSet.getString("lastname"));
            }
            logger.info("Get author by F&L name from DB");
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
        return authors;
    }

    //изменение автора
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

            logger.info("Update author from DB");
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

    //удаление автора
    @Override
    public void remove(Authors authors) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM authors WHERE id=?";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, authors.getId());

            preparedStatement.executeUpdate();

            logger.info("Delete author from DB");

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
