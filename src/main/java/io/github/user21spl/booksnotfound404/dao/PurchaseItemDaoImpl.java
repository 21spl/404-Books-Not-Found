package io.github.user21spl.booksnotfound404.dao;


import io.github.user21spl.booksnotfound404.model.PurchaseItem;
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
public class PurchaseItemDaoImpl implements PurchaseItemDao{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public void savePurchaseItem(PurchaseItem purchaseItem)
    {
        getSession().save(purchaseItem);
    }
    
    @Override
    public List<PurchaseItem> findItemsByPurchaseId(int purchaseId) {
        String hql = "FROM PurchaseItem pi WHERE pi.purchase.purchaseId = :purchaseId";
        Query<PurchaseItem> query = getSession().createQuery(hql, PurchaseItem.class);
        query.setParameter("purchaseId", purchaseId);
        return query.list();
    }
}
