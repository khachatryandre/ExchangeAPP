package org.example.service;

import org.example.model.Currency;
import org.example.model.ExchangeRatesDto;
import org.example.model.User;
import org.example.model.UserType;
import org.example.repository.RatesRepository;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeServiceImpl {

    private final RateService ratesService = new RateService();

    public double convertCurrency(double amount, Currency fromCurrency, Currency toCurrency) {
        return ratesService.getRate(fromCurrency) / ratesService.getRate(toCurrency) * amount;
    }

}
