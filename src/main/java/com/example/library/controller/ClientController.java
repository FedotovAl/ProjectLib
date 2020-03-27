package com.example.library.controller;

import com.example.library.entity.Client;
import com.example.library.service.ClientService;

public class ClientController {
    ClientService clientService = new ClientService();

    public void addNewClient(Client client){
        clientService.addNewClient(client);
    }

    public void removeClientByID(Long id){
        clientService.removeClient(clientService.getClientByID(id));
    }

    public void updateClient(Client client){
        clientService.updateClient(client);
    }

    public Client getClientByID(Long id) {
        return clientService.getClientByID(id);
    }

    public void showAllClients(){
        clientService.showAllClients();
    }
}
