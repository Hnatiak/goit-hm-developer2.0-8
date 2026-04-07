package org.example.service;

import java.util.List;

import org.example.dao.ClientDaoService;
import org.example.model.Client;

public class ClientServiceImpl implements ClientService {

    private final ClientDaoService dao;

    public ClientServiceImpl(ClientDaoService dao) {
        this.dao = dao;
    }

    public long create(String name) {
        return dao.create(name);
    }

    public String getById(long id) {
        return dao.getById(id);
    }

    public void setName(long id, String name) {
        dao.setName(id, name);
    }

    public void deleteById(long id) {
        dao.deleteById(id);
    }

    public List<Client> listAll() {
        return dao.listAll();
    }
}