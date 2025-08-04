
package io.github.user21spl.booksnotfound404.dao;

// basic imports
import java.util.*;

import io.github.user21spl.booksnotfound404.model.Purchase;

public interface PurchaseDao {
    void savePurchase(Purchase purchase);
    List<Purchase> findPurchasesByUserId(int userId);
    Purchase findByPurchaseId(int purchaseId);
    List<Purchase> findAllPurchases();
    Purchase findByIdWithItems(int purchaseId);
    List<Purchase> findAllPurchasesWithUserAndItems();


}
