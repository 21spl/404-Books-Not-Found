
package io.github.user21spl.booksnotfound404.model;



// sql related imports
import javax.persistence.*;
import javax.validation.constraints.*;

// basic java imports
import java.util.*;



@Entity
@Table(name="cart")
public class Cart {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    
    
    @OneToOne
    @JoinColumn(name="userid")
    private User user;
    
    @OneToMany(mappedBy="cart", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<CartItem> listOfCartItems;
    
    private double totalAmount;
    
    // constructor

    public Cart() {
    }
    
    // getters and setters

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItems() {
        return listOfCartItems;
    }

    public void setCartItems(List<CartItem> listOfCartItems) {
        this.listOfCartItems = listOfCartItems;
    }

    public List<CartItem> getListOfCartItems() {
        return listOfCartItems;
    }

    public void setListOfCartItems(List<CartItem> listOfCartItems) {
        this.listOfCartItems = listOfCartItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    
    
    
    
}
