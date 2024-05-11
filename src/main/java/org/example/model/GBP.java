package org.example.model;

public class GBP extends Currency {
    public GBP(double rate) {
        super("GBP", rate);
    }

    public GBP() {
        super("GBP");
    }

    @Override
    public String getSymbol() {
        return "£";
    }
}