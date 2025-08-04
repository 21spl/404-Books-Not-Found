
package io.github.user21spl.booksnotfound404.dao;


// import the  Models
import io.github.user21spl.booksnotfound404.model.Cart;
import io.github.user21spl.booksnotfound404.model.CartItem;

// hibernate imports
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


// spring annotation imports
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


//basic imports
import java.util.*;

@Repository
@Transactional
public class CartItemDaoImpl implements CartItemDao{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public void addCartItem(CartItem cartItem)
    {
        getSession().save(cartItem);
    }
    
    @Override
    public void updateCartItem(CartItem cartItem)
    {
        getSession().update(cartItem);
    }
    
    @Override
    public  void removeCartItem(CartItem cartItem)
    {
        getSession().delete(cartItem);
    }
    
    @Override
    public CartItem findById(int itemId)
    {
        String hql = "FROM CartItem ci WHERE ci.itemId = :itemId";
        Query<CartItem> query = getSession().createQuery(hql, CartItem.class);
        query.setParameter("itemId", itemId);
        return query.uniqueResult();
    }
    
    @Override
    public List<CartItem> findItemsByCartId(int cartId)
    {
        String hql = "FROM CartItem ci WHERE ci.cart.cartId = :cartId";
        Query<CartItem> query = getSession().createQuery(hql, CartItem.class);
        query.setParameter("cartId", cartId);
        return query.list();     
    }
    
    @Override
    public CartItem findByBookAndCart(int bookId, int cartId)
    {
        String hql = "FROM CartItem ci WHERE ci.cart.cartId = :cartId AND ci.book.bookId = :bookId";
        Query<CartItem> query = getSession().createQuery(hql, CartItem.class);
        query.setParameter("cartId", cartId);
        query.setParameter("bookId", bookId);
        return query.uniqueResult();
    }
    
}
