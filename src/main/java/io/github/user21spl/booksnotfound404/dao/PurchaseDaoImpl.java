
package io.github.user21spl.booksnotfound404.dao;

import io.github.user21spl.booksnotfound404.model.Purchase;

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
public class PurchaseDaoImpl implements PurchaseDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void savePurchase(Purchase purchase) {
        getSession().save(purchase);  // Hibernate will cascade to PurchaseItems if configured
    }

    @Override
    public List<Purchase> findPurchasesByUserId(int userId) {
        String hql = "FROM Purchase p WHERE p.user.userId = :userId ORDER BY p.purchaseDate DESC";
        Query<Purchase> query = getSession().createQuery(hql, Purchase.class);
        query.setParameter("userId", userId);
        return query.list();
    }

    @Override
    public Purchase findByPurchaseId(int purchaseId) {
        return getSession().get(Purchase.class, purchaseId);
    }
    
    @Override
    public List<Purchase> findAllPurchases() {
        String hql = "FROM Purchase";
        Query<Purchase> query = getSession().createQuery(hql, Purchase.class);
        return query.getResultList();
    }
    
    @Override
    public Purchase findByIdWithItems(int purchaseId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT DISTINCT p FROM Purchase p "
                   + "LEFT JOIN FETCH p.items i "
                   + "LEFT JOIN FETCH i.book "
                   + "WHERE p.id = :id";

        return session.createQuery(hql, Purchase.class)
                      .setParameter("id", purchaseId)
                      .uniqueResult();
    }
    
    
  

    

    @Override
    public List<Purchase> findAllPurchasesWithUserAndItems() {
        String hql = "SELECT DISTINCT p FROM Purchase p " +
                 "JOIN FETCH p.user " +
                 "JOIN FETCH p.items i " +
                 "JOIN FETCH i.book " +
                 "ORDER BY p.purchaseDate DESC";
    
        
        
        
        return getSession().createQuery(hql, Purchase.class).getResultList();
    }
}

    
    
    

    
    

