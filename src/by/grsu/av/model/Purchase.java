package by.grsu.av.model;

import by.grsu.av.model.state.State;

/**
 * Created by ok2ju on 15.04.2015.
 */
public class Purchase {

    private User user;
    private Product product;
    private int cost;
    private State state;

    public Purchase(User user, Product product, int cost, State state) {
        this.user = user;
        this.product = product;
        this.cost = cost;
        this.state = state;
    }

    public User getUser() {
        return user;
    }


    public Product getProduct() {
        return product;
    }

    public int getCost() {
        return cost;
    }

    public State getState() {
        return state;
    }
}
