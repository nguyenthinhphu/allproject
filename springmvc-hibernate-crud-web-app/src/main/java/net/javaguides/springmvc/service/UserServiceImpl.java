package net.javaguides.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.javaguides.springmvc.dao.UserDAO;
import net.javaguides.springmvc.entity.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public List < User > getUsers() {
        return userDAO.getUsers();
    }

    @Override
    @Transactional
    public User getUser(int theId) {
        return userDAO.getUser(theId);
    }

}
