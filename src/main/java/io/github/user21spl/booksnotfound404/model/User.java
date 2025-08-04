
package io.github.user21spl.booksnotfound404.model;


import java.util.*;

// sql related imports
import javax.validation.constraints.*;
import javax.persistence.*;



@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    
    @NotBlank(message="Name is required")
    private String name;
    
    @NotBlank(message="Email is required")
    @Email(message="Invalid email")
    @Column(unique=true)
    private String email;
    
    
    @NotBlank(message="Password is required")
    @Size(min=6, message="Password must be atleast 6 characters long")
    private String password;
    
    @Column(nullable=false)
    private String role; //either CUSTOMER or ADMIN
    
    
    
    // list of Purchase
    @OneToMany(mappedBy = "user")
    private List<Purchase> purchases = new ArrayList<>();
    
    
    // Cart
    @OneToOne(mappedBy="user", cascade = CascadeType.ALL)
    private Cart cart;


    // constructor
    // A no-argument constructor is mandatory - as per Hibernate requirement
    public User()
    {
        super();
    }
    
    // getters and setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    } 
    
    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
