package com.example.library.dao;

import com.example.library.entity.Client;

import java.sql.SQLException;
import java.util.List;

public interface DAO<Entity, Key> {
    //create
    void add(Entity entity) throws SQLException;

    //read
    List<Entity> getAll() throws SQLException;

    Entity getByID(Key key) throws SQLException;

    //update
    void update(Entity entity) throws SQLException;

    //delete
    void remove(Entity entity) throws SQLException;
}
