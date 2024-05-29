package fr.amu.iut.exercice14;

import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;

public class MainPersonnes {

    private static SimpleListProperty<Personne> lesPersonnes;
    private static IntegerProperty ageMoyen;
    private static IntegerProperty nbParisiens;

    private static IntegerBinding calculAgeMoyen;
    private static IntegerBinding calculnbParisiens;

    public static void main(String[] args) {

        lesPersonnes = new SimpleListProperty<>(FXCollections.observableArrayList());
        ageMoyen = new SimpleIntegerProperty(0);
        nbParisiens = new SimpleIntegerProperty(0);

        initialiserBindings();

        // question1();
        question2();
    }

    private static void initialiserBindings() {
        calculAgeMoyen = new IntegerBinding() {
            {
                super.bind(lesPersonnes);
                lesPersonnes.addListener((ListChangeListener<Personne>) change -> {
                    while (change.next()) {
                        if (change.wasAdded() || change.wasRemoved()) {
                            for (Personne p : change.getAddedSubList()) {
                                super.bind(p.ageProperty());
                            }
                            for (Personne p : change.getRemoved()) {
                                super.unbind(p.ageProperty());
                            }
                        }
                    }
                });
            }

            @Override
            protected int computeValue() {
                if (lesPersonnes.isEmpty()) {
                    return 0;
                }
                int totalAge = lesPersonnes.stream().mapToInt(Personne::getAge).sum();
                return totalAge / lesPersonnes.size();
            }
        };

        ageMoyen.bind(calculAgeMoyen);

        calculnbParisiens = new IntegerBinding() {
            {
                super.bind(lesPersonnes);
                lesPersonnes.addListener((ListChangeListener<Personne>) change -> {
                    while (change.next()) {
                        if (change.wasAdded() || change.wasRemoved()) {
                            for (Personne p : change.getAddedSubList()) {
                                super.bind(p.villeDeNaissanceProperty());
                            }
                            for (Personne p : change.getRemoved()) {
                                super.unbind(p.villeDeNaissanceProperty());
                            }
                        }
                    }
                });
            }

            @Override
            protected int computeValue() {
                return (int) lesPersonnes.stream()
                        .filter(p -> "Paris".equals(p.getVilleDeNaissance()))
                        .count();
            }
        };

        nbParisiens.bind(calculnbParisiens);
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
        lesPersonnes.clear();
        lesPersonnes.add(new Personne("Pierre", 20));
        lesPersonnes.add(new Personne("Paul", 40));

        System.out.println("Il y a " + nbParisiens.getValue() + " parisiens");
        lesPersonnes.get(0).setVilleDeNaissance("Marseille");
        System.out.println("Il y a " + nbParisiens.getValue() + " parisiens");
        lesPersonnes.get(1).setVilleDeNaissance("Montpellier");
        System.out.println("Il y a " + nbParisiens.getValue() + " parisiens");
        for (Personne p : lesPersonnes)
            p.setVilleDeNaissance("Paris");
        System.out.println("Il y a " + nbParisiens.getValue() + " parisiens");
    }

}
