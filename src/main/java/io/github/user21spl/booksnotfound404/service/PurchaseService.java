/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package io.github.user21spl.booksnotfound404.service;


import java.util.List;
import io.github.user21spl.booksnotfound404.model.Purchase;

public interface PurchaseService {
    void createPurchase(Purchase purchase);
    Purchase findById(int purchaseId);
    List<Purchase> findAllPurchaseByUserId(int userId);
    List<Purchase> findAllPurchases(); // for admin dashboard
    Purchase findByIdWithItems(int purchaseId);
    List<Purchase> getAllPurchasesWithDetails();
    
}
