package org.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.example.ui.ExchangeScreen;
import org.example.ui.LoginScreen;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Currency Exchange");

        LoginScreen loginScreen = new LoginScreen(primaryStage); // Pass the primaryStage
        ExchangeScreen exchangeScreen = new ExchangeScreen();

        primaryStage.setScene(loginScreen.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void onHelloButtonClick(ActionEvent actionEvent) {

    }
}