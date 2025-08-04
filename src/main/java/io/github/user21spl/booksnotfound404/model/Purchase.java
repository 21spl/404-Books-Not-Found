
package io.github.user21spl.booksnotfound404.model;

// basic java imports
import java.time.LocalDateTime;
import java.util.*;

// sql related imports
import javax.validation.constraints.*;
import javax.persistence.*;
import java.io.*;

import java.time.LocalDateTime;


/**
 *
 * @author 21pau
 */
@Entity
@Table(name="purchase")
public class Purchase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int purchaseId;
    
    private LocalDateTime purchaseDate;
    
    @ManyToOne
    @JoinColumn(name="userid")
    private User user;
    // Many purchases can be mapped to one user
    
    
   @OneToMany(mappedBy="purchase", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<PurchaseItem> items = new ArrayList<>();
    
    // method to add items in the purchase
    public void addItem(PurchaseItem item)
    {
        items.add(item);
        item.setPurchase(this);
    }
    
    private String phone;
    
    private String address;
    
    
    private double totalAmount;
    
    // no-arg constructor

    public Purchase() {
    }
    
    // setter and getter
    
    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PurchaseItem> getItems() {
        return items;
    }

    public void setItems(List<PurchaseItem> items) {
        this.items = items;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotalAmount() {
       return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    

}
