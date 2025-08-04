
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
public class CartDaoImpl implements CartDao{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public Cart findCartByUserId(int userId)
    {
        String hql = "FROM Cart c WHERE c.user.userId = :userId";
        Query<Cart> query = getSession().createQuery(hql, Cart.class);
        query.setParameter("userId", userId);
        return query.uniqueResult();
    }
    
    @Override
    public void saveCart(Cart cart)
    {
        getSession().saveOrUpdate(cart); // for new and existing carts
    }
    
    
    @Override
    public void updateCart(Cart cart) {
    Session session = sessionFactory.getCurrentSession();
    session.update(cart);
    }
    
    
    
    
    @Override
    public void clearCart(Cart cart)
    {
        // remove all cart items
        List<CartItem> items = cart.getCartItems();
        if(items!=null)
        {
            for(CartItem item: items)
            {
                getSession().delete(item);
            }
            items.clear(); // clear from java list
        }
        //optionally update the cart
        getSession().update(cart);
    }
    // This method deletes All CartItem entitites associated with a cart
}
