package fr.amu.iut.exercice1;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

@SuppressWarnings("Duplicates")
public class Palette extends Application {

    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private Label texteDuHaut;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Pane panneau;
    private HBox boutons;

    private Label texteDuBas;

    private SimpleIntegerProperty nbFois;
    private SimpleStringProperty message;
    private SimpleStringProperty couleurPanneau;

    private void createBindings() {
        BooleanProperty pasEncoreDeClic = new SimpleBooleanProperty();
        pasEncoreDeClic.bind(nbFois.isEqualTo(0));

        texteDuHaut.textProperty().bind(Bindings.when(pasEncoreDeClic)
                .then("Aucune couleur choisie")
                .otherwise(Bindings.concat(message, " choisi ", nbFois, " fois")));

        texteDuBas.textProperty().bind(Bindings.when(pasEncoreDeClic)
                .then("Aucune couleur choisie")
                .otherwise(Bindings.concat("Le ", message, " est une jolie couleur !")));
        texteDuBas.styleProperty().bind(Bindings.when(pasEncoreDeClic)
                .then("-fx-text-fill: #000000")
                .otherwise(Bindings.concat("-fx-text-fill: ", couleurPanneau)));
        panneau.styleProperty().bind(Bindings.concat("-fx-background-color: ", couleurPanneau));
    }

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();

        texteDuHaut = new Label();
        texteDuHaut.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(texteDuHaut, Pos.CENTER);

        panneau = new Pane();
        panneau.setPrefSize(400, 200);

        VBox bas = new VBox();
        boutons = new HBox(10);
        boutons.setAlignment(Pos.CENTER);
        boutons.setPadding(new Insets(10, 5, 10, 5));
        texteDuBas = new Label();
        bas.setAlignment(Pos.CENTER_RIGHT);
        bas.getChildren().addAll(boutons, texteDuBas);

        nbFois = new SimpleIntegerProperty(0);
        message = new SimpleStringProperty("N/A");
        couleurPanneau = new SimpleStringProperty("#FFFFFF");

        vert = new Button("Vert");
        rouge = new Button("Rouge");
        bleu = new Button("Bleu");

        vert.setOnAction(event -> {
            ++nbVert;
            nbFois.set(nbVert);
            message.setValue("Vert");
            couleurPanneau.setValue("#77DD77");
        });

        rouge.setOnAction(event -> {
            ++nbRouge;
            nbFois.set(nbRouge);
            message.setValue("Rouge");
            couleurPanneau.setValue("#FF6961");
        });

        bleu.setOnAction(event -> {
            ++nbBleu;
            nbFois.set(nbBleu);
            message.setValue("Bleu");
            couleurPanneau.setValue("#5B8899");
        });

        createBindings();

        boutons.getChildren().addAll(vert, rouge, bleu);

        root.setCenter(panneau);
        root.setTop(texteDuHaut);
        root.setBottom(bas);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
