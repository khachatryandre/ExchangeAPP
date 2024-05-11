package org.example.model;

public class USD extends Currency {
    public USD(double rate) {
        super("USD", rate);
    }

    public USD() {
        super("USD");
    }

    @Override
    public String getSymbol() {
        return "$";
    }
}