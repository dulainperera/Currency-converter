package ui;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CurrencyConverterApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        ComboBox<String> fromBox = new ComboBox<>();
        ComboBox<String> toBox = new ComboBox<>();
        TextField amountField = new TextField();
        Label resultLabel = new Label();
        Button convertButton = new Button();

        fromBox.getItems().addAll("USD", "EUR", "IND", "GBP", "JPY", "CNY");
        toBox.getItems().addAll("USD", "EUR", "IND", "GBP", "JPY", "CNY");
        fromBox.setValue("USD");
        toBox.setValue("LKR");

        convertButton.setOnAction(e ->{
            try {

            } catch (Exception ex) {
                result
            }
        });





    }
}
