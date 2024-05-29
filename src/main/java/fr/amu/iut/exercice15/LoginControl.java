package fr.amu.iut.exercice15;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class LoginControl extends GridPane {
    @FXML
    private TextField userId;

    @FXML
    private PasswordField pwd;

    @FXML
    private Button okBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Text msgErreur;


    @FXML
    public void initialize() {
        createBindings();
    }

    private void createBindings() {
        userId.textProperty().addListener((observable, oldValue, newValue) -> {
            pwd.setEditable(newValue.length() >= 6);
            if (newValue.length() < 6) {
                pwd.clear();
            }
        });

        BooleanBinding areFieldsEmpty = Bindings.createBooleanBinding(() ->
                        userId.getText().isEmpty() && pwd.getText().isEmpty(),
                userId.textProperty(),
                pwd.textProperty()
        );
        cancelBtn.disableProperty().bind(areFieldsEmpty);

        BooleanBinding isPasswordValid = Bindings.createBooleanBinding(() ->
                        pwd.getText().length() >= 8 &&
                                pwd.getText().matches(".*[A-Z].*") &&
                                pwd.getText().matches(".*[0-9].*"),
                pwd.textProperty()
        );
        okBtn.disableProperty().bind(isPasswordValid.not());

        pwd.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < 8) {
                msgErreur.setText("Le mot de passe doit faire 8 caractères ou plus");
            } else if (!newValue.matches(".*[A-Z].*")) {
                msgErreur.setText("Le mot de passe doit contenir au moins 1 majuscule");
            } else if (!newValue.matches(".*[0-9].*")) {
                msgErreur.setText("Le mot de passe doit contenir au moins 1 chiffre");
            } else {
                msgErreur.setText("");
            }
        });
    }

    @FXML
    private void okClicked() {
        System.out.print(userId.getText() + " ");
        for (char c : pwd.getText().toCharArray()) {
            System.out.print("*");
        }
        System.out.println();
    }

    @FXML
    private void cancelClicked() {
        userId.setText("");
        pwd.setText("");
    }
}
