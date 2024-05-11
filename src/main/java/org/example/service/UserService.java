package org.example.service;

import org.example.model.User;
import org.example.model.UserType;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository = new UserRepository();

    public User parseUserFromString(String s) {
        String[] userDetails = s.split(" ");
        return new User(userDetails[0], userDetails[1], UserType.valueOf(userDetails[2]));
    }

    public User loginUser(String email, String password) {
        String line = userRepository.loadUser(email);
        User user = parseUserFromString(line);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean registerUser(String email, String password, String userType) {
        return userRepository.saveUser(new User(email, password, UserType.valueOf(userType)));
    }
}