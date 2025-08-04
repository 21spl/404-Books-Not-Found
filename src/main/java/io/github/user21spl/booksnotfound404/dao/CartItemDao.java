
package io.github.user21spl.booksnotfound404.dao;


// basic imports
import java.util.*;

// import models
import io.github.user21spl.booksnotfound404.model.CartItem;

public interface CartItemDao {
    void addCartItem(CartItem cartItem);
    void updateCartItem(CartItem cartItem);
    void removeCartItem(CartItem cartItem);
    CartItem findById(int id);
    List<CartItem> findItemsByCartId(int cartId); // Gets all CartItems for a given Cart
    CartItem findByBookAndCart(int bookId, int cartId); // avoids duplicates
    // Ensures one book isn;t added twice - helps updating quantity
}
