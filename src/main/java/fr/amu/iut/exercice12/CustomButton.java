package fr.amu.iut.exercice12;

import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Button;

public class CustomButton extends Button {
    private final String color;
    private final javafx.beans.property.IntegerProperty nbClics = new javafx.beans.property.SimpleIntegerProperty(this, "nbClics", 0);

    public CustomButton(String text, String color) {
        super(text);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public int getNbClics() {
        return nbClics.get();
    }

    public void incrementNbClics() {
        nbClics.set(nbClics.get() + 1);
    }

    public javafx.beans.property.IntegerProperty nbClicsProperty() {
        return nbClics;
    }
}
