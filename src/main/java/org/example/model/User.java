package org.example.model;

public class User {
    private String email;
    private String password;
    private UserType userType;

    public User(String email, String password, UserType userType) {
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        return "Email: " + email + ", Password: " + password + ", UserType: " + userType;
    }

    public String userDetails() {
        return email + " " + password + " " + userType;
    }
}
