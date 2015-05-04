package by.grsu.av.engine;

import by.grsu.av.model.Product;
import by.grsu.av.model.Purchase;
import by.grsu.av.model.User;
import by.grsu.av.model.UserRole;
import by.grsu.av.model.state.AdminState;
import by.grsu.av.model.state.PlayerState;
import by.grsu.av.model.state.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameFacade {

    private final static int GOOD_NUMBER = 2;
    private boolean isStarted;
    private int matchId;
    private int setId;
    private int gameId;
    private List<Product> products;
    private final static Random random = new Random();


    private static GameFacade instance;

    public static GameFacade getInstance() {
        if(instance == null) instance = new GameFacade();
        return instance;
    }

    private int getMaxSets() {
        return GOOD_NUMBER * getProductCount();
    }

    private int getProductCount() {
        return Math.round((float) UserFacade.getInstance().getUserCount() / 2);
    }

    private List<Product> initializeProductList() {
        int productCount = getProductCount();
        List<Product> products = new ArrayList<Product>();
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
        matchId++;
        setId = 0;
        gameId = 0;

        products = initializeProductList();

        //final User user = new User("Lesha", UserRole.Player, 100);
        final List<User> users = UserFacade.getInstance().getUsers();

        final Thread daemon = new Thread(() -> {
            while(!products.isEmpty()) {
                // get random product from products-list
                int index = random.nextInt(products.size());
                Product product = products.get(index);

                System.out.println("Current Product is: " + product.getTitle());

                while(product.getPrice() > 0 && !product.isBought()) {
                    int currentPrice = product.getPrice();
                    int newPrice = calculateNewPrice(currentPrice);
                    product.setPrice(newPrice);

                    System.out.println("Product: " + product.getTitle() + " Price: " + newPrice);

                    if(currentPrice - newPrice == 3) {
                        int userNumber = random.nextInt(users.size());
                        User user = users.get(userNumber);
                        buy(user, product, newPrice);
                    }

                    // time to decrement price
                    int m = randInt(5, 20);
                    try {
                        Thread.sleep(m * 10);
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                products.remove(index);
                getInstance().nextSet(); // start next set
            }
            stopMath();

        });

        daemon.start(); // thread start
    }

    public void stopMath() {
        isStarted = false;
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

    public void buy(User user, Product product, int purchaseCost) {
        int money = user.getMoney();
        if(money > product.getPrice() && money > 0) {
            System.out.println("User: " + user.getUsername() +
                    " bought + " + product.getTitle() + " price : " + product.getPrice());
            product.setIsBought(true);
            user.setMoney(money - product.getPrice());
            HistoryFacade.getInstance().addPurchase(new Purchase(user, product, purchaseCost, getState()));
        }
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
}
