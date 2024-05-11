package org.example.repository;

import org.example.model.Currency;
import org.example.model.ExchangeRatesDto;
import org.example.service.RateService;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class RatesRepository {

    private static final String fileName = "exchangeRates.txt";
    private static final Path filePath = Paths.get("src", "main", "java", "org","example", "dao", fileName);


    public void addExchangeRates(ExchangeRatesDto exchangeRatesDto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.newLine();
            writer.write(exchangeRatesDto.toString());
            System.out.println("Rates added successfully");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public String loadRates(String date) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(date)) {
                    return line;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return null;
    }
}
