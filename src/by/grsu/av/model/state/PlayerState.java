package by.grsu.av.model.state;

import by.grsu.av.model.Product;

public class PlayerState extends State {

    private Product product;

    public PlayerState(int matchId, int setId, int gameId) {
        super(matchId, setId, gameId);
    }

    public PlayerState(int matchId, int setId, int gameId, Product product) {
        this(matchId, setId, gameId);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
