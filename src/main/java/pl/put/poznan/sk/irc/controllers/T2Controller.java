package pl.put.poznan.sk.irc.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import pl.put.poznan.sk.irc.IRC;

import java.io.IOException;

public class T2Controller {
    @FXML
    private VBox roomsList;

    @FXML
    void initialize() {
        setPanel("/fxml/welcome.fxml");
        IRC.connectionGod.getConnectedProperty().addListener(
                (observable, oldValue, newValue) -> roomsList.getChildren().removeAll(roomsList.getChildren()));
    }

    //  ===============   INTERFACE FUNCTIONS   ===============
    private void setPanel(String path) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(path));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ignored) {
        }
        roomsList.getChildren().add(root);
    }

    //  ===============   OTHERS   ===============

}
