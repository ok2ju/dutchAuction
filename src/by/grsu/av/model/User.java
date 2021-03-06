package by.grsu.av.model;

import by.grsu.av.model.state.PlayerState;
import by.grsu.av.model.state.State;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private UserRole role;
    private int money;
    private int score;

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

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
