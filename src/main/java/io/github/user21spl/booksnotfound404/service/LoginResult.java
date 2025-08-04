
package io.github.user21spl.booksnotfound404.service;


import io.github.user21spl.booksnotfound404.model.User;


// A response wrapper class
public class LoginResult {
    
    private boolean success;
    private String message;
    private User user;
    
    // constructor
    public LoginResult(boolean success, String message, User user)
    {
        this.success = success;
        this.message = message;
        this.user = user;
    }
    
    // check if the logged user is admin or not
    public boolean isAdmin()
    {
        return "ADMIN".equalsIgnoreCase(user.getRole()) && user!=null;
    }
    
    // Getters and Setters

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
}
