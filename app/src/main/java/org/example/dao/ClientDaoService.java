package org.example.dao;

import java.util.List;

import org.example.model.Client;

public interface ClientDaoService {
    long create(String name);
    String getById(long id);
    void setName(long id, String name);
    void deleteById(long id);
    List<Client> listAll();
}