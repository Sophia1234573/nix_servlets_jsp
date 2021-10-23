package com.solutions.denisovich.model.dao;

import com.solutions.denisovich.model.entity.Role;

public interface RoleDao extends Dao<Role> {
    void create(Role role);
    void update(Role role);
    void remove(Role role);
    Role findByName(String name);
}
