
package io.github.user21spl.booksnotfound404.model;


import javax.persistence.*;
import javax.validation.constraints.*; 
import java.util.*;


@Entity
@Table(name="books")
public class Book{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    
    @NotBlank(message = "title is required")
    private String title;
    
    @NotBlank(message = "Author name is required")
    private String author;
    
    @Column(length=512)
    private String description;
    
    private String genre;
    
    @NotBlank(message = "No. of copies are required")
    private int copies;
    
    @NotBlank(message = "Price is required")
    private double price;
    
    @OneToMany(mappedBy="book")
    private List<PurchaseItem> purchaseItems;
    
    
    @OneToMany(mappedBy="book")
    private List<CartItem> cartItems;
    
    
    
    // no-argument constructor for Hibernate
    public Book() {
        super();
    }
    
    //getters and setters

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

   
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }

    public void setPurchaseItems(List<PurchaseItem> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    
    
  
}
