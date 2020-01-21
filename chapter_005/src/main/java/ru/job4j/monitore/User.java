package ru.job4j.monitore;

public class User {
    private int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addAmount(int transferSum) {
        this.amount = this.amount + transferSum;
    }

    public int getId() {
        return id;
    }
}
