package fr.amu.iut.exercice14;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;

public class MainPersonnes {

    private static SimpleListProperty<Personne> lesPersonnes;
    private static IntegerProperty ageMoyen;
    private static IntegerProperty nbParisiens;

    public static void main(String[] args) {

        lesPersonnes = new SimpleListProperty<>(FXCollections.observableArrayList());
        ageMoyen = new SimpleIntegerProperty(0);
        nbParisiens = new SimpleIntegerProperty(0);

        lesPersonnes.addListener((ListChangeListener<Personne>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    for (Personne p : c.getAddedSubList()) {
                        p.ageProperty().addListener((observable, oldValue, newValue) -> updateAgeMoyen());
                        p.villeDeNaissanceProperty().addListener((observable, oldValue, newValue) -> updateNbParisiens());
                    }
                }
                if (c.wasRemoved()) {
                    for (Personne p : c.getRemoved()) {
                        p.ageProperty().removeListener((observable, oldValue, newValue) -> updateAgeMoyen());
                        p.villeDeNaissanceProperty().removeListener((observable, oldValue, newValue) -> updateNbParisiens());
                    }
                }
                updateAgeMoyen();
                updateNbParisiens();
            }
        });

        question1();
        question2();
    }

    private static void updateAgeMoyen() {
        if (lesPersonnes.isEmpty()) {
            ageMoyen.set(0);
            return;
        }
        int totalAge = 0;
        for (Personne p : lesPersonnes) {
            totalAge += p.getAge();
        }
        ageMoyen.set(totalAge / lesPersonnes.size());
    }

    private static void updateNbParisiens() {
        int count = 0;
        for (Personne p : lesPersonnes) {
            if ("Paris".equals(p.getVilleDeNaissance())) {
                count++;
            }
        }
        nbParisiens.set(count);
    }

    public static void question1() {
        System.out.println("1 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        Personne pierre = new Personne("Pierre", 20);
        lesPersonnes.add(pierre);
        System.out.println("2 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        Personne paul = new Personne("Paul", 40);
        lesPersonnes.add(paul);
        System.out.println("3 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(jacques);
        System.out.println("4 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        paul.setAge(100);
        System.out.println("5 - L'age moyen est de " + ageMoyen.getValue() + " ans");
        lesPersonnes.remove(paul);
        System.out.println("6 - L'age moyen est de " + ageMoyen.getValue() + " ans");
    }


    public static void question2() {
        System.out.println("Il y a " + nbParisiens.getValue() + " parisiens");
        lesPersonnes.get(0).setVilleDeNaissance("Marseille");
        System.out.println("Il y a " + nbParisiens.getValue() + " parisiens");
        lesPersonnes.get(1).setVilleDeNaissance("Montpellier");
        System.out.println("Il y a " + nbParisiens.getValue() + " parisien");
        for (Personne p : lesPersonnes)
            p.setVilleDeNaissance("Paris");
        System.out.println("Il y a " + nbParisiens.getValue() + " parisiens");
    }

}

