package pl.put.poznan.sk.irc.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pl.put.poznan.sk.irc.IRC;

public class WelcomeController {
    @FXML
    private Label welcomeLabel;

    @FXML
    void initialize() {
        displayMessage(IRC.connectionManager.getConnectedProperty().getValue());
        IRC.connectionManager.getConnectedProperty().addListener((observable, oldValue, newValue) -> displayMessage(newValue));
        IRC.connectionManager.setMessagesController(null);
    }

    private void setLabelText(String text) {
        welcomeLabel.setText(text);
    }

    private void displayMessage(boolean value) {
        if (value) {
            setLabelText("Witaj " + IRC.connectionConfiguration.getUsername() + " na serwerze SK-IRC!\nOd teraz możesz dołączyć do jednej z wielu prowadzonych rozmów.");
        } else {
            setLabelText("Nie nawiązano połączenia z serwerem czatu. Proszę zmienić ustawienia.");
        }
    }
}
