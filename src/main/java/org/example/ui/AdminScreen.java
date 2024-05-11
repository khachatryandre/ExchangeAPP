package org.example.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.Currencies;
import org.example.model.ExchangeRatesDto;
import org.example.model.USD;
import org.example.model.EUR;
import org.example.model.GBP;
import org.example.service.RateService;
import org.example.service.TransactionService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AdminScreen extends Scene {
    private final DatePicker rateDatePicker = new DatePicker();
    private final Map<Currencies, TextField> rateFields = new HashMap<>();
    private final Button addRateButton = new Button("Add Rates");

    private final RateService rateService;
    private final TransactionService transactionService;

    public AdminScreen(RateService rateService, TransactionService transactionService) {
        super(new BorderPane(), 800, 600);
        this.rateService = rateService;
        this.transactionService = transactionService;
        setupUI();
    }

    private void setupUI() {
        BorderPane layout = (BorderPane) getRoot();
        layout.setPadding(new Insets(20));

        // Add New Rates Form
        GridPane rateForm = new GridPane();
        rateForm.setHgap(10);
        rateForm.setVgap(10);
        rateForm.setPadding(new Insets(10));

        rateForm.add(new Label("Date:"), 0, 0);
        rateForm.add(rateDatePicker, 1, 0);

        int rowIndex = 1;
        for (Currencies currency : Currencies.values()) {
            TextField rateField = new TextField();
            rateFields.put(currency, rateField);
            rateForm.add(new Label(currency + ":"), 0, rowIndex);
            rateForm.add(rateField, 1, rowIndex);
            rowIndex++;
        }

        addRateButton.setOnAction(event -> handleAddRates());
        rateForm.add(addRateButton, 1, rowIndex);

        layout.setTop(rateForm);

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(event -> handleLogout());
        BorderPane layout1 = (BorderPane) getRoot();
        layout1.setTop(new VBox(10, rateForm, logoutButton));
    }

    private void handleAddRates() {
        try {
            LocalDate date = rateDatePicker.getValue();
            if (date == null) {
                throw new IllegalArgumentException("Please select a date.");
            }

            double usdRate = Double.parseDouble(rateFields.get(Currencies.USD).getText());
            double eurRate = Double.parseDouble(rateFields.get(Currencies.EUR).getText());
            double gbpRate = Double.parseDouble(rateFields.get(Currencies.GBP).getText());
            ExchangeRatesDto newRates = new ExchangeRatesDto(date.toString(), new USD(usdRate), new EUR(eurRate), new GBP(gbpRate)/*, ... other currencies */);
            rateService.addExchangeRate(newRates);
            showSuccessAlert();
            clearInputFields();
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numbers for the rates.");
        } catch (IllegalArgumentException e) {
            showAlert("Invalid Input", e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Rates successfully added.");
        alert.showAndWait();
    }

    private void clearInputFields() {
        rateDatePicker.setValue(null);
        for (TextField field : rateFields.values()) {
            field.clear();
        }
    }

    private void handleLogout() {
        // Logic to handle logout (e.g., clear session data)

        // Assuming you have a reference to the Stage
        Stage currentStage = (Stage) getWindow();
        // Create a new LoginScreen instance and set it as the current scene
        currentStage.setScene(new LoginScreen(currentStage));
    }
}
