
package io.github.user21spl.booksnotfound404.dao;

import io.github.user21spl.booksnotfound404.model.User;

import java.util.*;

public interface UserDao {
    void saveUser(User user); // For registration
    User findById(int id);
    User findByEmail(String email);  // For login
    User findByEmailAndPassword(String email, String password);
    
    List<User> findAllUsers();
    
    void updateUser(User user);
    void deleteUser(int id);
}
