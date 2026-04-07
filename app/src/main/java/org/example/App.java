package org.example;

import org.example.dao.ClientDaoService;
import org.example.dao.ClientDaoServiceImpl;
import org.example.service.ClientService;
import org.example.service.ClientServiceImpl;

public class App {
    public static void main(String[] args) {

        ClientDaoService dao = new ClientDaoServiceImpl();
        ClientService service = new ClientServiceImpl(dao);

        long id = service.create("Test Client");
        System.out.println(service.getById(id));
        System.out.println(service.listAll());
    }
}