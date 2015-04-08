package by.grsu.av.engine;

import by.grsu.av.model.Product;
import by.grsu.av.model.User;
import by.grsu.av.model.UserRole;
import by.grsu.av.model.state.AdminState;
import by.grsu.av.model.state.PlayerState;
import by.grsu.av.model.state.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameFacade {

    private final int GOOD_NUMBER = 2;
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
        return Math.round(UserFacade.getInstance().getUserCount() / 2);
    }

    private List<Product> initializeProductList() {
        int productCount = getProductCount();
        List<Product> products = new ArrayList<>();
        products.add(new Product("product 1", 100, productCount));
        products.add(new Product("product 2", 100, productCount));
        return products;
    }

    public void startMath() {
        isStarted = true;
        matchId++;
        setId = 0;
        gameId = 0;

        products = initializeProductList();
    }

    public void stopMath() {
        isStarted = false;
    }

    public void nextSet() {
        if(setId < getMaxSets()) {
            setId++;
        } else {
            stopMath();
        }
    }

    public State getState(User user) {
        State result = null;

        if(isStarted) {
            if(user.getRole().equals(UserRole.Admin)) {
                result = new AdminState(matchId, setId, gameId);
            } else if(user.getRole().equals(UserRole.Player)) {
                result = new PlayerState(matchId, setId, gameId);
            }
            // else {throw new NotImplementedException();}
        }

        return result;
    }

    private Thread getPriceLesserThread(final Product currentSetProduct) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                while(currentSetProduct.getPrice() > 0 && currentSetProduct.getCount() > 0) {
                    int currentPrice = currentSetProduct.getPrice();
                    int newPrice = calculateNewPrice(currentPrice);
                    currentSetProduct.setPrice(newPrice);

                    System.out.println("Product : " + currentSetProduct.getTitle() + " price: " + newPrice);

                    int m = randInt(5, 20);
                    try {
                        Thread.sleep(m * 100);
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                currentSetProduct.setCount(0);//product disappear
            }
        });
    }

    public void buy(User user, Product product) {
        System.out.println("User: " + user.getUsername() +
                " bougth + " + product.getTitle() + " price : " + product.getPrice());

        product.setCount(0);

    }

    private static int calculateNewPrice(int currentPrice) {
        int k = randInt(1, 10);
        int newPrice = currentPrice - k;
        return newPrice < 0 ? 0 : newPrice;
    }

    private static int randInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public List<Product> getProducts() {
        return products;
    }

    private static Thread runUserThread(final User user, final Product product) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int k = randInt(2, 15);
                try {
                    Thread.sleep(k * 10);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }

                if(product.getCount() > 0 && user.getMoney() >= product.getPrice()) {
                    getInstance().buy(user, product);
                }
            }
        });
        return thread;
    }

    public static void main(String[] args) throws InterruptedException {
        User player1 = LoginFacade.getInstance().login("player1", UserRole.Player);
        User player2 = LoginFacade.getInstance().login("player2", UserRole.Player);

        GameFacade gameFacade = getInstance();
        gameFacade.startMath();

        int setNumber = getInstance().getMaxSets();
        for(int i = 0; i < setNumber; i++) {
            gameFacade.nextSet();
            Product currentProduct = gameFacade.getProducts().get(i);
            System.out.println("p1: " + gameFacade.getState(player1));
            System.out.println("p2: " + gameFacade.getState(player2));
            Thread thread = gameFacade.getPriceLesserThread(currentProduct);
            Thread p1 = runUserThread(player1, currentProduct);
            Thread p2 = runUserThread(player2, currentProduct);
            thread.start();
            p1.start();
            p2.start();
            thread.join();
            p1.join();
            p2.join();
        }
    }
}
