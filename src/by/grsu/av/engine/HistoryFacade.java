package by.grsu.av.engine;

import by.grsu.av.model.Purchase;
import by.grsu.av.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ok2ju on 15.04.2015.
 */
public class HistoryFacade {
    private List<Purchase> purchases;

    private static HistoryFacade instance;

    private HistoryFacade() {
        purchases = new ArrayList<Purchase>();
    }

    public static HistoryFacade getInstance() {
        if (instance == null) {
            instance = new HistoryFacade();
        }
        return instance;
    }

    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public int calcScore(User user) {
        Set<String> boughTypes = new HashSet<String>();
        int result;
        for (Purchase purchase : purchases) {
            String productTitle = purchase.getProduct().getTitle();
            if (purchase.getUser().equals(user) && !boughTypes.contains(productTitle)) {
                boughTypes.add(productTitle);
            }
        }
        result = boughTypes.size() * 20 + (ConfigFacade.getProductTypes().length - boughTypes.size()) * (-20);
        result += (ConfigFacade.getProductTypes().length == boughTypes.size()) ? boughTypes.size() * 2 * user.getMoney() : user.getMoney();
        return result;
    }

}
