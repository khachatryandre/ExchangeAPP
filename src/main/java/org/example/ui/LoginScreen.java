package org.example.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.User;
import org.example.model.UserType;
import org.example.service.RateService;
import org.example.service.TransactionService;
import org.example.service.UserService;

public class LoginScreen extends Scene{
    private final UserService userService = new UserService();
    private final RateService rateService = new RateService();
    private final TransactionService transactionService = new TransactionService();
    private ExchangeScreen exchangeScreen = new ExchangeScreen();
    private Stage stage;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button registerButton;
    private Label statusLabel;

    public LoginScreen(Stage stage) {
        super(new VBox(), 600, 400);
        this.stage = stage;
        initializeComponents();
        createLayout();
        attachEventHandlers();
    }

    private void initializeComponents() {
        usernameField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Login");
        statusLabel = new Label();
        registerButton = new Button("Register");
        registerButton.setOnAction(event -> handleRegister());

        VBox layout = (VBox) getRoot(); // Get the root VBox
        layout.getChildren().add(registerButton);
    }

    private VBox createLayout() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));

        layout.getChildren().addAll(
                new Label("Username:"), usernameField,
                new Label("Password:"), passwordField,
                loginButton, statusLabel, registerButton
        );
        return layout;
    }

    private void attachEventHandlers() {
        loginButton.setOnAction(e -> {
            User user = userService.loginUser(usernameField.getText(), passwordField.getText());
            if (user != null) {
                statusLabel.setText("Login Successful!");
                if (user.getUserType() == UserType.ADMIN) {
                    stage.setScene(new AdminScreen(rateService, transactionService));
                } else if (user.getUserType() == UserType.USER) {
                    stage.setScene(new ExchangeScreen().getScene());
                }
            }  else {
                statusLabel.setText("Invalid Credentials");
            }
        });
    }

    public Scene getScene() {
        Scene scene = new Scene(createLayout(), 300, 250);
        return scene;
    }

    private void handleRegister() {
        Stage registerStage = new Stage();
        registerStage.setTitle("User Registration");
        registerStage.setScene(new RegisterScreen(registerStage, userService, this));
        registerStage.show();
        stage.close();
    }
}
