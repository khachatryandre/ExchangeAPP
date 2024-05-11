package org.example.model;

public class AMD extends Currency {

    public AMD() {
        super("USD");
    }

    @Override
    public String getSymbol() {
        return "€";
    }
}