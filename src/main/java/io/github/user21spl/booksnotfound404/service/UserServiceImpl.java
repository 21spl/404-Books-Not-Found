package io.github.user21spl.booksnotfound404.service;


import java.util.*;

// import DAOs and Models
import io.github.user21spl.booksnotfound404.model.User;
import io.github.user21spl.booksnotfound404.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    
    
    @Autowired
    private UserDao userDao;
    
    // Registration
    @Override
    public boolean registerUser(User user)
    {
        // check if email already exists
        if(userDao.findByEmail(user.getEmail())!=null) return false;
        
        userDao.saveUser(user);
        return true;
    }
    
    
    //login and validation
    @Override
    public LoginResult authenticate(String email, String password)
    {
        // first try to find the user by email - in login, user first enters email
        User user = userDao.findByEmail(email);
        
        if(user==null)
        {
            // no account registered with the email
            return new LoginResult(false, "No account found. Please register first", null);
        }
        if(!user.getPassword().equals(password))
        {
            return new LoginResult(false, "Incorrect password. Please try again", null);
        }
        
        return new LoginResult(true, "Login successful", user);
    }
    
    @Override
    public User findUserByEmail(String email)
    {
        return userDao.findByEmail(email);
    }
    
    @Override
    public User findUserById(int id)
    {
        return userDao.findById(id);
    }
    
    @Override
    public User findUserByEmailAndPassword(String email, String password)
    {
        return userDao.findByEmailAndPassword(email, password);
    }
    
    @Override
    public List<User> findAllUsers()
    {
        return userDao.findAllUsers();
    }
    
    @Override
    public void updateUser(User user)
    {
        userDao.updateUser(user);
    }
    
    @Override
    public void deleteUser(int userId)
    {
        userDao.deleteUser(userId);
    }
}



/*
We define a DAO interface (UserDao) and an implementation class (UserDaoImpl).
Spring sees @Repository on UserDaoImpl and creates a bean of that class.
Anywhere we write @Autowired UserDao userDao;, Spring injects the UserDaoImpl bean.
*/