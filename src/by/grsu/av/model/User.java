package by.grsu.av.model;

/**
 * Created by ok2ju on 12.03.2015.
 */
public class User {

    private String username;
    private UserRole role;
    private int money;

    public User(String username, UserRole role) {
        this.username = username;
        this.role = role;
    }

    public User(String username, UserRole role, int money) {
        this(username, role);
        this.money = money;
    }

    public String getUsername() {
        return username;
    }

    public UserRole getRole() {
        return role;
    }

    public int getMoney() {
        return money;
    }
}
