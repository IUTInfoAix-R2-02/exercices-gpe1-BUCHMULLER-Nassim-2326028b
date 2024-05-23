package fr.amu.iut.exercice9;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Animation extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        CustomButton customButton = new CustomButton();
        root.setCenter(customButton);
        Scene scene = new Scene(root, 400, 400);

        Duration duration = Duration.millis(1500);
        TranslateTransition transition1 = new TranslateTransition(duration, customButton);
        transition1.setByX(150);

        TranslateTransition transition2 = new TranslateTransition(duration, customButton);
        transition2.setByY(-150);

        TranslateTransition transition3 = new TranslateTransition(duration, customButton);
        transition3.setByX(-scene.getWidth());

        TranslateTransition transition4 = new TranslateTransition(duration, customButton);
        transition4.setByY(-scene.getHeight());

        SequentialTransition sequentialTransition = new SequentialTransition(transition1, transition2, transition3, transition4);
        sequentialTransition.setAutoReverse(true);
        sequentialTransition.setCycleCount(2);

        customButton.setOnMousePressed(mouseEvent -> sequentialTransition.play());

        primaryStage.setTitle("Animation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
