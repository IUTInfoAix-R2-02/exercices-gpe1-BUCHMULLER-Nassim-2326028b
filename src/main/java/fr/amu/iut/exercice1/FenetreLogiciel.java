package fr.amu.iut.exercice1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Orientation;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FenetreLogiciel extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox myVBox = new VBox(8);
        myVBox.setAlignment(Pos.CENTER_LEFT);

        Text ButtonLabel = new Text();
        ButtonLabel.setFont(new Font(13));
        ButtonLabel.setText("  Boutons : ");

        Button b1 = new Button ("Bouton 1");
        Button b2 = new Button ("Bouton 2");
        Button b3 = new Button ("Bouton 3");
        myVBox.getChildren().addAll(ButtonLabel, b1, b2, b3);

        Menu menu1 = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem openMenuItem = new MenuItem("Open");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem closeMenuItem = new MenuItem("Close");
        menu1.getItems().addAll(newMenuItem, openMenuItem, saveMenuItem, closeMenuItem);

        Menu menu2 = new Menu("Options");
        MenuItem cutMenuItem = new MenuItem("Cut");
        MenuItem copyMenuItem = new MenuItem("Copy");
        MenuItem pasteMenuItem = new MenuItem("Paste");
        menu2.getItems().addAll(cutMenuItem, copyMenuItem, pasteMenuItem);

        Menu menu3 = new Menu("Help");
        MenuBar menuBar = new MenuBar(menu1, menu2, menu3);

        BorderPane bPane = new BorderPane();

        HBox hbox = new HBox(5, myVBox, new Separator(Orientation.VERTICAL));
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPadding(new Insets(0, 0, 0, 10));

        bPane.setLeft(hbox);
        bPane.setTop(menuBar);

        GridPane formGrid = new GridPane();

        TextField nomField = new TextField();
        TextField prenomField = new TextField();
        TextField emailField = new TextField();

        Button submitButton = new Button("Submit");
        Button cancelButton = new Button("Cancel");
        HBox confirmButtons = new HBox(5, submitButton, cancelButton);

        formGrid.add(new Label("Nom:"), 0, 0);
        formGrid.add(nomField, 1, 0);
        formGrid.add(new Label("Prénom:"), 0, 1);
        formGrid.add(prenomField, 1, 1);
        formGrid.add(new Label("Email:"), 0, 2);
        formGrid.add(emailField, 1, 2);
        formGrid.add(confirmButtons, 1, 3);

        confirmButtons.setAlignment(Pos.CENTER);
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setPadding(new Insets(20));

        Text basLabel = new Text();
        basLabel.setFont(new Font(13));
        basLabel.setText("Ceci est un label de bas de page");

        VBox btnLabel = new VBox(0, new Separator(Orientation.HORIZONTAL), basLabel);
        btnLabel.setAlignment(Pos.CENTER);

        bPane.setBottom(btnLabel);
        bPane.setCenter(formGrid);
        bPane.setLeft(hbox);
        bPane.setTop(menuBar);

        Scene myScene = new Scene (bPane, 500, 500);

        primaryStage.setScene(myScene);
        primaryStage.setTitle("Exercice 1");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}