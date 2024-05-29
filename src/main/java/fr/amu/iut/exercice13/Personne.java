package fr.amu.iut.exercice13;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Personne {

    private String nom;
    private int age;
    private StringProperty villeDeNaissance;

    public Personne(String nom, int age) {
        this.nom = nom;
        this.age = age;
        this.villeDeNaissance = new SimpleStringProperty("Paris");

        this.villeDeNaissance.addListener((obs, oldAge, newAge) ->
                System.out.println(this.nom + " a maintenant " + newAge + " ans"));
    }

    public String getNom() {
        return nom;
    }

    public void setAge(int age) {
        this.age = age;
        this.villeDeNaissance.set(String.valueOf(age));
    }

    public int getAge() {
        return age;
    }

    public Observable ageProperty() {
        return villeDeNaissance;
    }
}
