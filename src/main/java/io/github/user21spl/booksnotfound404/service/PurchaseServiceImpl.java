/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.user21spl.booksnotfound404.service;

import io.github.user21spl.booksnotfound404.model.*;
import io.github.user21spl.booksnotfound404.dao.*;

import java.util.*;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PurchaseServiceImpl  implements PurchaseService{
    
    @Autowired
    private PurchaseDao purchaseDao;
    
    @Override
    public void createPurchase(Purchase purchase)
    {
        purchaseDao.savePurchase(purchase);
    }
    
    @Override
    public Purchase findById(int purchaseId)
    {
        return purchaseDao.findByPurchaseId(purchaseId);
    }
    
    @Override
    public List<Purchase> findAllPurchaseByUserId(int userId)
    {
        return purchaseDao.findPurchasesByUserId(userId);
    }
    
    @Override
    public List<Purchase> findAllPurchases()
    {
        return purchaseDao.findAllPurchases();
    }
    
    @Override
    public Purchase findByIdWithItems(int purchaseId) {
        return purchaseDao.findByIdWithItems(purchaseId);
    }
    
    @Override
    public List<Purchase> getAllPurchasesWithDetails() {
        return purchaseDao.findAllPurchasesWithUserAndItems();
    }

    
}
