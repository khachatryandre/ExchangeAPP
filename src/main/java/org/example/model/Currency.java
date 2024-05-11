package org.example.model;

public abstract class Currency {
    private String code;
    private Double rate;

    public Currency(String code) {
        this.code = code;
    }
    public Currency(String code, double rate) {
        this.code = code;
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }
    public Double getRate() {
        return rate;
    }

    public abstract String getSymbol();

    @Override
    public String toString() {
        return code + ":" + rate;
    }

    public static Currency fromString(String code) {
        switch (code) {
            case "USD": return new USD();
            case "EUR": return new EUR();
            case "GBP": return new GBP();
            default: return null;
        }
    }
}