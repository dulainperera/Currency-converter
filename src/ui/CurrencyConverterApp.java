package ui;

import api.ExchangeRateServices;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CurrencyConverterApp extends Application {

    @Override
    public void start(Stage primaryStage) {

        //set title
        primaryStage.setTitle("Currency Converter");

        //ui components
        ComboBox<String> fromBox = new ComboBox<>();
        ComboBox<String> toBox = new ComboBox<>();
        TextField amountField = new TextField();
        Label resultLabel = new Label("Converted Amount");
        Button convertButton = new Button("Convert");

        fromBox.getItems().addAll("LKR", "USD", "EUR", "INR", "GBP", "JPY", "CNY");
        toBox.getItems().addAll("LKR", "USD", "EUR", "INR", "GBP", "JPY", "CNY");

        //set default values
        fromBox.setValue("USD");
        toBox.setValue("LKR");


        //layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(new Label("From:"), 0, 0);
        grid.add(fromBox, 1, 0);
        grid.add(new Label("To:"), 0, 1);
        grid.add(toBox, 1, 1);
        grid.add(new Label("Amount:"), 0, 2);
        grid.add(amountField, 1, 2);
        grid.add(convertButton, 0, 3);
        grid.add(resultLabel, 1,3, 2, 1);

        //button action
        convertButton.setOnAction(e ->{
            try {
                String from = fromBox.getValue().toUpperCase().trim();
                String to = toBox.getValue().toUpperCase().trim();
                double amount = Double.parseDouble(amountField.getText());

                double rate = ExchangeRateServices.getExchangeRate(from, to);

                if (rate == -1) {
                    resultLabel.setText("Failed to fetch exchange rate.");
                } else {
                    double converted = rate * amount;
                    resultLabel.setText(String.format("%.2f %s =%.2f %s", amount, from, converted, to));
                }

            } catch (Exception ex) {
                resultLabel.setText("Error:" + ex.getMessage());
            }
        });

        //scene
        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    //main class
    public static void main(String[] args) {
        launch(args);
    }

}
