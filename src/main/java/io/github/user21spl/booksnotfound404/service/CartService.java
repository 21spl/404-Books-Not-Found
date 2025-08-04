
package io.github.user21spl.booksnotfound404.service;


import io.github.user21spl.booksnotfound404.model.Cart;
import io.github.user21spl.booksnotfound404.model.CartItem;
import io.github.user21spl.booksnotfound404.model.Book;
import io.github.user21spl.booksnotfound404.model.User;

import java.util.List;

public interface CartService {
    
    Cart getCartByUser(User user);
    void updateCartItem(CartItem item);
    void addBookToCart(User user, Book book, int quantity);
    void updateCartItemQuantity(int cartItemId, int quantity);
    void removeCartItem(CartItem item);
    void clearCart(User user);
    List<CartItem> getCartItems(User user);
    double calculateCartTotal(User user);
}
