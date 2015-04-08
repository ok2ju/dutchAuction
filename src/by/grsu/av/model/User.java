package by.grsu.av.model;

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
