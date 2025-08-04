
package io.github.user21spl.booksnotfound404.dao;


// basic imports
import java.util.*;

// import models
import io.github.user21spl.booksnotfound404.model.Cart;

public interface CartDao {
    Cart findCartByUserId(int userId);
    void saveCart(Cart cart);
    void updateCart(Cart cart);
    void clearCart(Cart cart); // for checkout
}
