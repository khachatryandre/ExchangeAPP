package org.example.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.UserType;
import org.example.service.UserService;

public class RegisterScreen extends Scene {

    private TextField emailField;
    private PasswordField passwordField;
    private Button registerButton;
    private Label statusLabel;
    private final UserService userService;
    private final Scene loginScreen;
    private Stage stage;

    public RegisterScreen(Stage stage, UserService userService, Scene loginScreen) {
        super(new GridPane(), 400, 300); // Set the scene to use a GridPane layout
        this.stage = stage;
        this.userService = userService;
        this.loginScreen = loginScreen;
        initializeComponents();
        createLayout();
        attachEventHandlers();
    }

    private void initializeComponents() {
        emailField = new TextField();
        emailField.setPromptText("Email");
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        registerButton = new Button("Register");
        statusLabel = new Label();
    }

    private GridPane createLayout() {
        GridPane layout = (GridPane) getRoot(); // Get the GridPane root of the scene
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(25, 25, 25, 25));

        layout.add(new Label("Email:"), 0, 0);
        layout.add(emailField, 1, 0);

        layout.add(new Label("Password:"), 0, 1);
        layout.add(passwordField, 1, 1);

        layout.add(registerButton, 1, 2);
        layout.add(statusLabel, 1, 3);

        return layout;
    }

    // ... (initializeComponents, createLayout methods similar to LoginScreen)

    private void attachEventHandlers() {
        registerButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();
            boolean success = userService.registerUser(email, password, String.valueOf(UserType.USER));

            if (success) {
                showAlert("Success", "User registered successfully!");
                stage.setScene(loginScreen); // Switch back to login screen
            } else {
                showAlert("Error", "Failed to register user. Please try again.");
            }
        });
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}