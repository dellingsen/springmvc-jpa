package com.home.webapps.dao;

import java.util.List;

import com.home.webapps.domain.User;

public interface UserDao
{
    public User findById(long id);

    public User findByUserName(String name, String pass);
    
    public List<User> findAllOrderedByName();

    public void save(User user);

    public void delete(User user);
}
