package org.example.service;

import org.example.model.ExchangeRatesDto;
import org.example.model.*;
import org.example.repository.RatesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

public class RateService {

    private final RatesRepository ratesRepository = new RatesRepository();

    public ExchangeRatesDto parseRateFromString(String s) {
        String[] rates = s.split(" ");
        return new ExchangeRatesDto(rates[0], new USD(Double.parseDouble(rates[1].split(":")[1])),
                new EUR(Double.parseDouble(rates[2].split(":")[1])),
                new GBP(Double.parseDouble(rates[3].split(":")[1])));
    }

    public ExchangeRatesDto loadRates() {
        return parseRateFromString(ratesRepository.loadRates(LocalDate.now().toString()));
    }

    public void addExchangeRate(ExchangeRatesDto exchangeRatesDto) {
            ratesRepository.addExchangeRates(exchangeRatesDto);
    }

    public double getRate(Currency currency) {
        ExchangeRatesDto exchangeRatesDto = loadRates();
        return switch (currency.getCode()) {
            case "USD" -> exchangeRatesDto.getUSD().getRate();
            case "EUR" -> exchangeRatesDto.getEUR().getRate();
            case "GBP" -> exchangeRatesDto.getGBP().getRate();
            default -> 1d;
        };
    }
}
