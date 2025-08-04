package io.github.user21spl.booksnotfound404.model;

// sql related imports
import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name="cart_item")
public class CartItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    
    private int quantity;
    
    @ManyToOne
    @JoinColumn(name="cartId")
    private Cart cart;
    
    @ManyToOne
    @JoinColumn(name="bookId")
    private Book book;
    
    private double subTotal;
    
    // constructor

    public CartItem() {
    }
    
    // getters and setters

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

}
