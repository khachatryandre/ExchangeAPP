package org.example.model;

public class ExchangeRatesDto {
    private String date;
    private USD usd;
    private EUR euro;
    private GBP gbp;

    public ExchangeRatesDto(String date, USD usd, EUR euro, GBP gbp) {
        this.date = date;
        this.usd = usd;
        this.euro = euro;
        this.gbp = gbp;
    }

    public String getDate() {
        return date;
    }

    public USD getUSD() {
        return usd;
    }

    public EUR getEUR() {
        return euro;
    }

    public GBP getGBP() {
        return gbp;
    }

    public String toString() {
        return date + " " + usd.toString() + " " + euro.toString() + " " + gbp.toString();
    }
}
