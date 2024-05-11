package org.example.service;

import org.example.model.Currency;

public interface CurrencyExchangeService {

    public abstract double convertCurrency(double amount, Currency fromCurrency, Currency toCurrency);
}