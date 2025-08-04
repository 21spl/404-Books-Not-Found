
package io.github.user21spl.booksnotfound404.dao;


import java.util.*;

import io.github.user21spl.booksnotfound404.model.Purchase;
import io.github.user21spl.booksnotfound404.model.PurchaseItem;

public interface PurchaseItemDao {
    void savePurchaseItem(PurchaseItem purchaseItem);
    List<PurchaseItem> findItemsByPurchaseId(int purchaseId);
}
