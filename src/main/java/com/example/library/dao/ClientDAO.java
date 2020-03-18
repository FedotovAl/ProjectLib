package com.example.library.dao;

import com.example.library.bl.Util;
import com.example.library.entity.Client;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ClientDAO extends Util implements DAO<Client, Long> {
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
    public List<Client> getAll() throws SQLException{
        Connection connection = getConnection();

        List<Client> clientsList = new ArrayList<>();

        String sql = "SELECT * FROM clients";

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
        return clientsList;
    }

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
        return client;
    }

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
    public void remove(Client client) throws SQLException{
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM clients WHERE id=?";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, client.getId());

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
