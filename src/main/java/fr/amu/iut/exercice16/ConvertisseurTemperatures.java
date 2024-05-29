package fr.amu.iut.exercice16;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class ConvertisseurTemperatures extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label celsiusLabel = new Label("°C");
        Slider celsiusSlider = new Slider(0, 100, 0);
        celsiusSlider.setOrientation(javafx.geometry.Orientation.VERTICAL);
        TextField celsiusTextField = new TextField("0");

        Label fahrenheitLabel = new Label("°F");
        Slider fahrenheitSlider = new Slider(32, 212, 32);
        fahrenheitSlider.setOrientation(javafx.geometry.Orientation.VERTICAL);
        TextField fahrenheitTextField = new TextField("32");

        VBox panneauCelsius = new VBox(10, celsiusLabel, celsiusSlider, celsiusTextField);
        panneauCelsius.setAlignment(Pos.CENTER);
        VBox panneauFahrenheit = new VBox(10, fahrenheitLabel, fahrenheitSlider, fahrenheitTextField);
        panneauFahrenheit.setAlignment(Pos.CENTER);
        HBox root = new HBox(30, panneauCelsius, panneauFahrenheit);
        root.setPadding(new Insets(20));

        DoubleProperty celsius = new SimpleDoubleProperty(0);
        DoubleProperty fahrenheit = new SimpleDoubleProperty(32);

        celsius.bindBidirectional(celsiusSlider.valueProperty());
        fahrenheit.bindBidirectional(fahrenheitSlider.valueProperty());

        ChangeListener<Number> celsiusChangeListener = (obs, oldVal, newVal) -> {
            double newFahrenheit = newVal.doubleValue() * 9 / 5 + 32;
            if (fahrenheit.get() != newFahrenheit) {
                fahrenheit.set(newFahrenheit);
            }
        };

        ChangeListener<Number> fahrenheitChangeListener = (obs, oldVal, newVal) -> {
            double newCelsius = (newVal.doubleValue() - 32) * 5 / 9;
            if (celsius.get() != newCelsius) {
                celsius.set(newCelsius);
            }
        };

        celsius.addListener(celsiusChangeListener);
        fahrenheit.addListener(fahrenheitChangeListener);

        celsiusTextField.textProperty().bindBidirectional(celsiusSlider.valueProperty(), new NumberStringConverter());
        fahrenheitTextField.textProperty().bindBidirectional(fahrenheitSlider.valueProperty(), new NumberStringConverter());

        primaryStage.setTitle("Convertisseur de Températures");
        primaryStage.setScene(new Scene(root, 200, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
