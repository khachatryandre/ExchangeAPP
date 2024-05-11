package org.example.model;

public class Transaction {

    private String date;
    private User user;
    private Currency from;
    private Currency to;
    private double amount;

    public Transaction(String date, User user, Currency from, Currency to, double amount) {
        this.date = date;
        this.user = user;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Currency getFrom() {
        return from;
    }

    public void setFrom(Currency from) {
        this.from = from;
    }

    public Currency getTo() {
        return to;
    }

    public void setTo(Currency to) {
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String toString() {
        return date + " " + user.getEmail() + " " + from.toString() + " " + to.toString() + " " + amount;
    }
}
