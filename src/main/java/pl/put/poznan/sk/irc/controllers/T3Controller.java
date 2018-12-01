package pl.put.poznan.sk.irc.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import pl.put.poznan.sk.irc.IRC;

import java.io.IOException;

public class T3Controller {
    @FXML
    private VBox usersList;

    @FXML
    void initialize() {
        setPanel("/fxml/userTemplate.fxml");
        setPanel("/fxml/userTemplate.fxml");
        setPanel("/fxml/userTemplate.fxml");
        setPanel("/fxml/userTemplate.fxml");
        IRC.connectionGod.getConnectedProperty().addListener(
                (observable, oldValue, newValue) -> usersList.getChildren().removeAll(usersList.getChildren()));
    }

    private void setPanel(String path) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(path));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ignored) {
        }
        usersList.getChildren().add(root);
    }
}
