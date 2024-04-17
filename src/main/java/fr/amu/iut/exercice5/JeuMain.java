package fr.amu.iut.exercice5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JeuMain extends Application {

    private Scene scene;
    private BorderPane root;

    @Override
    public void start(Stage primaryStage) {

        root = new BorderPane();

        //Acteurs du jeu
        Personnage pacman = new Pacman();
        Personnage fantome = new Fantome();
        Obstacle obstacle = new Obstacle();
        // on positionne le fantôme 20 positions vers la droite
        fantome.setLayoutX(20 * 10);
        obstacle.setLayoutX(5*10);
        obstacle.setLayoutY(5*10);
        //panneau du jeu
        Pane jeu = new Pane();
        jeu.setPrefSize(640, 480);
        jeu.getChildren().add(pacman);
        jeu.getChildren().add(fantome);
        root.setCenter(jeu);
        root.getChildren().add(obstacle);
        //on construit une scene 640 * 480 pixels
        scene = new Scene(root);

        //Gestion du déplacement du personnage
        deplacer(pacman, fantome);

        primaryStage.setTitle("PAC-MAN");

        primaryStage.setScene(scene);
        primaryStage.show();
        root.setStyle("-fx-background-color: black;");
    }


    private void deplacer(Personnage j1, Personnage j2) {
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case LEFT:
                    if (j1.getLayoutX() == 160)
                        if (j1.getLayoutY()==100)
                            break;
                    if (j1.getLayoutX() == 160)
                        if (j1.getLayoutY()==120)
                            break;
                    if (j1.getLayoutX() == 160)
                        if (j1.getLayoutY()==140)
                            break;
                    j1.deplacerAGauche();
                    break;
                case RIGHT:
                    if (j1.getLayoutX() == 80)
                        if (j1.getLayoutY()==100)
                            break;
                    if (j1.getLayoutX() == 80)
                        if (j1.getLayoutY()==120)
                            break;
                    if (j1.getLayoutX() == 80)
                        if (j1.getLayoutY()==140)
                            break;
                    j1.deplacerADroite(scene.getWidth());
                    break;
                case DOWN:
                    if (j1.getLayoutX() == 100)
                        if (j1.getLayoutY()==80)
                            break;
                    if (j1.getLayoutX() == 120)
                        if (j1.getLayoutY()==80)
                            break;
                    if (j1.getLayoutX() == 140)
                        if (j1.getLayoutY()==80)
                            break;
                    j1.deplacerEnBas(scene.getHeight());
                    break;
                case UP:
                    if (j1.getLayoutX() == 100)
                        if (j1.getLayoutY()==160)
                            break;
                    if (j1.getLayoutX() == 120)
                        if (j1.getLayoutY()==160)
                            break;
                    if (j1.getLayoutX() == 140)
                        if (j1.getLayoutY()==160)
                            break;
                    j1.deplacerEnHaut();
                    break;
                case Z:
                    if (j2.getLayoutX() == 100)
                        if (j2.getLayoutY()==160)
                            break;
                    if (j2.getLayoutX() == 120)
                        if (j2.getLayoutY()==160)
                            break;
                    if (j2.getLayoutX() == 140)
                        if (j2.getLayoutY()==160)
                            break;
                    j2.deplacerEnHaut();
                    break;
                case S:
                    if (j2.getLayoutX() == 100)
                        if (j2.getLayoutY()==80)
                            break;
                    if (j2.getLayoutX() == 120)
                        if (j2.getLayoutY()==80)
                            break;
                    if (j2.getLayoutX() == 140)
                        if (j2.getLayoutY()==80)
                            break;
                    j2.deplacerEnBas(scene.getHeight());
                    break;
                case Q:
                    if (j2.getLayoutX() == 160)
                        if (j2.getLayoutY()==100)
                            break;
                    if (j2.getLayoutX() == 160)
                        if (j2.getLayoutY()==120)
                            break;
                    if (j2.getLayoutX() == 160)
                        if (j2.getLayoutY()==140)
                            break;
                    j2.deplacerAGauche();
                    break;
                case D:
                    if (j2.getLayoutX() == 80)
                        if (j2.getLayoutY()==100)
                            break;
                    if (j2.getLayoutX() == 80)
                        if (j2.getLayoutY()==120)
                            break;
                    if (j2.getLayoutX() == 80)
                        if (j2.getLayoutY()==140)
                            break;
                    j2.deplacerADroite(scene.getWidth());
                    break;
            }
            if (j1.estEnCollision(j2)) {
                System.out.println("Joueur 1 a gagné !");
                System.exit(0);
            }

        });
    }
}