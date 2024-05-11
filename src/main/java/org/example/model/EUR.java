package org.example.model;

public class EUR extends Currency {

    public EUR(double rate) {
        super("EUR", rate);
    }

    public EUR() {
        super("EUR");
    }

    @Override
    public String getSymbol() {
        return "€";
    }
}