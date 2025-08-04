
package io.github.user21spl.booksnotfound404.model;

import java.time.LocalDateTime;
import java.util.*;
import javax.validation.constraints.*;
import javax.persistence.*;


@Entity
@Table(name="purchase_item")
public class PurchaseItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    
    private int quantity;
    private double priceAtPurchase;
    
    @ManyToOne
    @JoinColumn(name="purchaseId")
    private Purchase purchase;
    
    
    @ManyToOne
    @JoinColumn(name="bookId")
    private Book book;
    
    //constructor

    public PurchaseItem() {
    }
    
    // getters and setters

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(double priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    
    
    
}
