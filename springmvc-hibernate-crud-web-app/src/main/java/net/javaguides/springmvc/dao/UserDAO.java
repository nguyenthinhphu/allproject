package net.javaguides.springmvc.dao;

import java.util.List;

import net.javaguides.springmvc.entity.Customer;
import net.javaguides.springmvc.entity.User;

public interface UserDAO {

    public List <User> getUsers();
    
    public User getUser(int theId);

}

