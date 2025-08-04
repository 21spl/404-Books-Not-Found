
package io.github.user21spl.booksnotfound404.model;
import java.time.LocalDateTime;

public class Feedback {
    
    private int token; //feedback token

    private int userId;
    private String name;    
    private String phone;    
    private String email; 
    private String feedback;
    private LocalDateTime date_submitted;

    //no-arg constructors

    public Feedback() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getToken() {    
        return token;    
    }    
    public void setToken(int token) {    
        this.token = token;    
    }

    

    public String getName() {    
        return name;    
    }
    public void setName(String name) {    
        this.name = name;    
    }
    public String getPhone() {    
        return phone;    
    }
    public void setPhone(String phone) {    
        this.phone = phone;    
    }
    public String getEmail() {    
        return email;    
    }
    public void setEmail(String email) {    
        this.email = email;    
    }
    public String getFeedback() {    
        return feedback;    
    }
    public void setFeedback(String feedback) {    
        this.feedback = feedback;    
    }

    public LocalDateTime getDate_submitted() {
        return date_submitted;
    }

    public void setDate_submitted(LocalDateTime date_submitted) {
        this.date_submitted = date_submitted;
    }
}
