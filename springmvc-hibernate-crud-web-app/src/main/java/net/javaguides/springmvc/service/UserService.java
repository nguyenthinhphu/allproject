package net.javaguides.springmvc.service;

import java.util.List;

import net.javaguides.springmvc.entity.User;


public interface UserService {

    public List < User > getUsers();

    public User getUser(int theId);


}
