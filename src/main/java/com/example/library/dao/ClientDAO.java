package com.example.library.dao;

import com.example.library.bl.Util;
import com.example.library.entity.Client;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

@NoArgsConstructor
public class ClientDAO extends Util implements DAO<Client, Long> {
    private static final Logger logger = Logger.getLogger(ClientDAO.class);
    //Добавление
    @Override
    public void add(Client client) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO clients (firstname, lastname) VALUES(?, ?)";
        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, client.getFirstname());
            preparedStatement.setString(2, client.getLastname());

            preparedStatement.executeUpdate();
            logger.info("Add client to DB");
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

    //Получение листа из всех Клиентов
    @Override
    public List<Client> getAll() throws SQLException{
        Connection connection = getConnection();

        List<Client> clientsList = new ArrayList<>();

        String sql = "SELECT * FROM clients ORDER BY id";

        Statement statement = null;
        try{
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Client client = new Client();
                client.setId(resultSet.getLong("id"));
                client.setFirstname(resultSet.getString("firstname"));
                client.setLastname(resultSet.getString("lastname"));

                clientsList.add(client);
            }
            logger.info("Get List of clients from DB");
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
        return clientsList;
    }

    //получение клиента по ID
    @Override
    public Client getByID(Long id) throws SQLException{
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM clients WHERE id=?";

        Client client = new Client();
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                client.setId(resultSet.getLong("id"));
                client.setFirstname(resultSet.getString("firstname"));
                client.setLastname(resultSet.getString("lastname"));
            }
            logger.info("Get client by ID from DB");
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
        return client;
    }

    //Обновление клиента
    @Override
    public void update(Client client) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "UPDATE clients SET firstname=?, lastname=? WHERE id=?";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, client.getFirstname());
            preparedStatement.setString(2, client.getLastname());
            preparedStatement.setLong(3, client.getId());

            preparedStatement.executeUpdate();
            logger.info("Update client from DB");
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

    //Удаление клиента
    @Override
    public void remove(Client client) throws SQLException{
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM clients WHERE id=?";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, client.getId());

            preparedStatement.executeUpdate();

            logger.info("Delete client from DB");

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
