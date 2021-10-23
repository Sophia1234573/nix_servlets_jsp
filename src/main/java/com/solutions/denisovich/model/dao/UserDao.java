package com.solutions.denisovich.model.dao;

import com.solutions.denisovich.model.entity.User;

import java.util.List;

public interface UserDao extends Dao<User> {
    void create(User user);
    void update(User user);
    void remove(User entity);
    List<User> findAll();
    User findByLogin(String login);
    User findByEmail(String email);
}
