package org.example.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.example.model.Currencies;
import org.example.model.Currency;
import org.example.model.ExchangeRatesDto;
import org.example.service.CurrencyExchangeServiceImpl;
import org.example.service.RateService;

public class ExchangeScreen extends Scene {
    private final ComboBox<String> fromCurrency = new ComboBox<>();
    private final ComboBox<String> toCurrency = new ComboBox<>();
    private final TextField amountField = new TextField();
    private final Button exchangeButton = new Button("Exchange");
    private final Label resultLabel = new Label();
    private final CurrencyExchangeServiceImpl currencyExchangeService = new CurrencyExchangeServiceImpl();
    private final RateService rateService = new RateService();
    private Label[] rateLabels = new Label[4];

    public ExchangeScreen() {
        super(new VBox(), 400, 300);
        initializeComponents();
        createLayout();
        attachEventHandlers();
        loadCurrentRates();
    }

    private void initializeComponents() {
        for (Currencies currency : Currencies.values()) {
            fromCurrency.getItems().add(currency.name());
            toCurrency.getItems().add(currency.name());
        }
    }

    private VBox createLayout() {
        VBox mainLayout = new VBox(10);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.getChildren().add(createRateDisplayGrid());
        mainLayout.getChildren().add(createExchangeFormLayout());
        return mainLayout;
    }

    private GridPane createRateDisplayGrid() {
        GridPane rateDisplayGrid = new GridPane();
        rateDisplayGrid.setHgap(10);
        rateDisplayGrid.setVgap(10);
        int rowIndex = 0;
        rateLabels = loadCurrentRates();
        for (Currencies currency : Currencies.values()) {
            Label currencyLabel = new Label(currency + ":");
            //rateLabels[rowIndex] = new Label();
            rateDisplayGrid.add(currencyLabel, 0, rowIndex);
            rateDisplayGrid.add(rateLabels[rowIndex], 1, rowIndex);
            rowIndex++;
        }
        return rateDisplayGrid;
    }

    private GridPane createExchangeFormLayout() {
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setHgap(10);
        layout.setVgap(10);

        layout.add(new Label("From:"), 0, 0);
        layout.add(fromCurrency, 1, 0);
        layout.add(new Label("To:"), 0, 1);
        layout.add(toCurrency, 1, 1);
        layout.add(new Label("Amount:"), 0, 2);
        layout.add(amountField, 1, 2);
        layout.add(exchangeButton, 0, 3);
        layout.add(resultLabel, 1, 3);
        return layout;
    }

    private void attachEventHandlers() {
        exchangeButton.setOnAction(e -> {
            CharSequence amount = amountField.getCharacters();
            System.out.println(amountField.textProperty().getValue());
            System.out.println(fromCurrency.getValue());
            System.out.println(toCurrency.getValue());
            Currency from = Currency.fromString(fromCurrency.getValue());
            Currency to = Currency.fromString(toCurrency.getValue());

            assert from != null;
            assert to != null;
            Double convertedAmount = currencyExchangeService.convertCurrency(Double.parseDouble(amountField.textProperty().getValue()), from, to);
            resultLabel.setText(String.format("Result: %.2f %s", convertedAmount, to.getCode()));
        });
    }

    public Scene getScene() {
        Scene scene = new Scene(createLayout(), 400, 300);
        return scene;
    }

    private Label[] loadCurrentRates() {
        Label[] rateLabels = new Label[4];
        rateLabels[0] = new Label();
        rateLabels[1] = new Label();
        rateLabels[2] = new Label();
        rateLabels[3] = new Label();
        ExchangeRatesDto rates = rateService.loadRates();

        if (rates != null) {
            rateLabels[0] = new Label("1");
            rateLabels[1] = new Label("386");
            rateLabels[2] = new Label("416");
            rateLabels[3] = new Label("445");
        }
        return rateLabels;
    }
}