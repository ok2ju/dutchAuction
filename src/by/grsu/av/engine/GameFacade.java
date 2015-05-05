package by.grsu.av.engine;

import by.grsu.av.model.Product;
import by.grsu.av.model.Purchase;
import by.grsu.av.model.User;
import by.grsu.av.model.UserRole;
import by.grsu.av.model.state.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameFacade {

    private boolean isStarted;
    private boolean isFinished;
    private int matchId;
    private int setId;
    private int gameId;
    private List<Product> products;
    private Product product;
    private final static Random random = new Random();

    private static GameFacade instance;
    public static GameFacade getInstance() {
        if(instance == null) instance = new GameFacade();
        return instance;
    }

    private int getMaxSets() {
        return ConfigFacade.getGoodNumber() * getProductCount();
    }

    private int getProductCount() {
        return Math.round((float) UserFacade.getInstance().getUserCount() / 2);
    }

    private List<Product> initializeProductList() {
        int productCount = getProductCount();
        List<Product> products = new ArrayList<>();
        while(productCount > 0) {
            for(String type : ConfigFacade.getProductTypes()) {
                products.add(new Product(type, 100));
            }
            productCount--;
        }
        return products;
    }

    public void startMath() {
        isStarted = true;
        isFinished = false;
        matchId++;
        setId = 0;
        gameId = 0;
        products = initializeProductList();
        final Thread daemon = new Thread(() -> {
            while(!products.isEmpty()) {
                product = randomProduct(products);
                while(priceAndNotBought(product)) {
                    changeProductPrice(product);
                    timeout();
                }
                products.remove(product);
                getInstance().nextSet();
            }
            stopMath();
        });
        daemon.start();
    }

    private void timeout() {
        int m = randInt(5, 20);
        try {
            Thread.sleep(m * 600);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void changeProductPrice(Product product) {
        int currentPrice = product.getPrice();
        int newPrice = calculateNewPrice(currentPrice);
        product.setPrice(newPrice);
    }

    private boolean priceAndNotBought(Product product) {
        return product.getPrice() > 0 && !product.isBought();
    }

    private Product randomProduct(List<Product> products) {
        int index = random.nextInt(products.size());
        return  products.get(index);
    }

    public void stopMath() {
        isStarted = false;
        isFinished = true;
        System.out.println("Match finished or stopped!");
        for(User user : UserFacade.getInstance().getUsers()) {
            int score = HistoryFacade.getInstance().calcScore(user);
            System.out.println("User :" + user.getUsername() + " score: " + score);
        }
    }

    public void nextSet() {
        if(setId < getMaxSets()) {
            setId++;
        } else {
            stopMath();
        }
    }

    public State getState() {
        return new State(matchId, setId, gameId);
    }

    public synchronized void buy(User user) {
        int money = user.getMoney();
        if(money > product.getPrice() && money > 0) {
            System.out.println("User: " + user.getUsername() +
                    " bought + " + product.getTitle() + " price : " + product.getPrice());
            product.setIsBought(true);
            user.setMoney(money - product.getPrice());
            HistoryFacade.getInstance().addPurchase(new Purchase(user, product, product.getPrice(), getState()));
        }
    }

    public Product getCurrentProduct() {
        return product;
    }

    public boolean isStarted() {
        return isStarted;
    }

    // decrement current price to random value
    private static int calculateNewPrice(int currentPrice) {
        int newPrice = currentPrice - randInt(1, 10);
        return newPrice < 0 ? 0 : newPrice;
    }

    private static int randInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static void main(String[] args) throws InterruptedException {
        LoginFacade.getInstance().login("player1", UserRole.Player);
        LoginFacade.getInstance().login("player2", UserRole.Player);
        LoginFacade.getInstance().login("player3", UserRole.Player);
        getInstance().startMath();
    }

    public boolean isFinished() {
        return isFinished;
    }
}
