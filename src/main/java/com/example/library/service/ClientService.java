package com.example.library.service;

import com.example.library.dao.ClientDAO;
import com.example.library.entity.Client;

import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;

public class ClientService {
    private static final Logger logger = Logger.getLogger(ClientService.class);

    //добавление нового клиента
    public void addNewClient(Client client){
        logger.info("Add new client");
        ClientDAO clientDAO = new ClientDAO();
        try {
            clientDAO.add(client);
            System.out.println("New client is created");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    //получение сущности клиента по ID
    public Client getClientByID(Long id){
        logger.info("Get client by ID");
        ClientDAO clientDAO = new ClientDAO();
        Client client = new Client();
        try {
            client = clientDAO.getByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
        if (client.getId() == null){
            System.out.println("Client does not exist");
            return null;
        } else {
        return client;
        }
    }


    //Удаление клиента
    public void removeClient(Client client){
        logger.info("Delete client");
        ClientDAO clientDAO = new ClientDAO();
        try {
            if (clientDAO.getByID(client.getId()).getId() != null){
                clientDAO.remove(client);
                System.out.println("Removing is completed");
            } else{
                System.out.println("Client does not exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    //Обновление записи о клиенте
    public void updateClient(Client client){
        logger.info("Update client");
        ClientDAO clientDAO = new ClientDAO();
        try {
            if (clientDAO.getByID(client.getId()).getId() != null){
                clientDAO.update(client);
                System.out.println("Updating is completed");
            } else{
                System.out.println("Client does not exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    //Вывод на экран списка всех клиентов
    public void showAllClients(){
        logger.info("Show all clients");
        ClientDAO clientDAO = new ClientDAO();
        try {
            List<Client> clientList = clientDAO.getAll();
            if (clientList.size() != 0) {
                for (Client a : clientList){
                    System.out.println(a);
                }
            } else {
                System.out.println("clientList is empty");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }
}
