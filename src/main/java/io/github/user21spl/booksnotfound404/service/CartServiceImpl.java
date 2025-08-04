
package io.github.user21spl.booksnotfound404.service;


import io.github.user21spl.booksnotfound404.model.*;
import io.github.user21spl.booksnotfound404.dao.*;

import java.util.*;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CartServiceImpl implements CartService {
    
    @Autowired
    private CartDao cartDao;
    @Autowired
    private CartItemDao cartItemDao;
    @Autowired
    private BookDao bookDao;
    
    @Override
    public Cart getCartByUser(User user)
    {
        int userId = user.getUserId();
        return cartDao.findCartByUserId(userId);
    }
    
    @Override
    public void addBookToCart(User user, Book book, int quantity)
    {
        Cart cart = cartDao.findCartByUserId(user.getUserId());
        if(cart == null)
        {
            cart = new Cart();
            cart.setUser(user);
            cartDao.saveCart(cart);
        }
        
        int bookId = book.getBookId();
        int cartId = cart.getCartId();
        CartItem existingItem = cartItemDao.findByBookAndCart(cartId, bookId);
        
        if(existingItem != null)
        {

            int newQuantity = existingItem.getQuantity() + quantity;
            if (newQuantity > book.getCopies()) {
                newQuantity = book.getCopies();
            }
            
            existingItem.setQuantity(newQuantity);
            existingItem.setSubTotal(newQuantity * book.getPrice());
            cartItemDao.updateCartItem(existingItem);
            
        }
        else
        {
            // add the book in the cart only if quantity<=stock
            int actualQuantity = Math.min(quantity, book.getCopies());
            CartItem newItem = new CartItem();
            newItem.setBook(book);
            newItem.setCart(cart);
            newItem.setQuantity(quantity);
            newItem.setSubTotal(actualQuantity * book.getPrice());
            cartItemDao.addCartItem(newItem);
        }
        
        // recalculate and update cart total
        List<CartItem> cartItems = cartItemDao.findItemsByCartId(cart.getCartId());
        double total = cartItems.stream()
                .mapToDouble(CartItem::getSubTotal)
                .sum();
        cart.setTotalAmount(total);
        cartDao.updateCart(cart);
                
    }
    
    @Override
    public void updateCartItemQuantity(int cartItemId, int quantity)
    {
        CartItem item = cartItemDao.findById(cartItemId);
        if(item!=null)
        {
            item.setQuantity(quantity);
            cartItemDao.updateCartItem(item);
        }  
    }
    
    @Override
    public void removeCartItem(CartItem item)
    {
        cartItemDao.removeCartItem(item);
    }
    
    @Override
    public void clearCart(User user)
    {
        Cart cart = cartDao.findCartByUserId(user.getUserId());
        int cartId = cart.getCartId();
        if(cart!=null)
        {
            List<CartItem> items = cartItemDao.findItemsByCartId(cartId);
            for(CartItem item: items)
            {
                cartItemDao.removeCartItem(item);
            }
        }
    }
    
    @Override
    public void updateCartItem(CartItem item)
    {
        cartItemDao.updateCartItem(item);
    }
    
    @Override
    public List<CartItem> getCartItems(User user)
    {
        Cart cart = cartDao.findCartByUserId(user.getUserId());
        if(cart !=null)
        {
            return cartItemDao.findItemsByCartId(cart.getCartId());
        }
        return new ArrayList<>();
    }
    
    @Override
    public double calculateCartTotal(User user)
    {
        double total = 0.0;
        List<CartItem> items = getCartItems(user);
        for(CartItem item: items)
        {
            total+= item.getBook().getPrice() * item.getQuantity();
        }
        return total;
    }
}
