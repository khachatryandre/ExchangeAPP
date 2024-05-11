package org.example.repository;

import org.example.model.Currency;
import org.example.model.ExchangeRatesDto;
import org.example.model.User;
import org.example.service.RateService;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TransactionRepository {

    private static final String fileName = "transactionHistory.txt";
    private static final Path filePath = Paths.get("src", "main", "java", "org","example", "dao", fileName);


    public void save(User user, Currency from,Currency to, double amount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            writer.newLine();
            writer.write(user.toString() + " " + from.toString() + " " + to.toString() + " " + amount);
            System.out.println("Rates added successfully");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
