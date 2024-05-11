package org.example.repository;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
public class UserRepository {
    private static final String fileName = "users.txt";
    private static final Path filePath = Paths.get("src", "main", "java", "org","example", "dao", fileName);


    public boolean saveUser(User user) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile(), true))) {
            if (loadUser(user.getEmail()) != null) {
                return false;
            }
            writer.newLine();
            writer.write(user.userDetails());
            System.out.println("User added successfully");
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
        return false;
    }

    public String loadUser(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(email)) {
                    return line;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return null;
    }
}
