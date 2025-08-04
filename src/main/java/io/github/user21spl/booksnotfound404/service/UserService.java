package io.github.user21spl.booksnotfound404.service;



import io.github.user21spl.booksnotfound404.model.User;

import java.util.List;


public interface UserService {
    
    //registration
    boolean registerUser(User user);
    
    // Authentication and Login

    /**
     *
     * @param email
     * @param password
     * @return
     */
    LoginResult authenticate(String email, String password);
    
    // Fetch
    User findUserById(int userId);
    User findUserByEmail(String email);
    User findUserByEmailAndPassword(String email, String password);
    List<User> findAllUsers();
    
    //update profile
    void updateUser(User user);
    
    // Delete user (for admin)
    void deleteUser(int userId);
}
