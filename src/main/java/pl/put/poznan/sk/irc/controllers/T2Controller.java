package pl.put.poznan.sk.irc.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import pl.put.poznan.sk.irc.IRC;

import java.io.IOException;

public class T2Controller {
    @FXML
    private VBox roomsList;
    @FXML
    private TextField roomName;

    @FXML
    void initialize() {
        setPanel();
        setPanel();
        IRC.connectionManager.getConnectedProperty().addListener(
                (observable, oldValue, newValue) -> roomsList.getChildren().removeAll(roomsList.getChildren()));
    }

    private void setPanel() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/roomTemplate.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ignored) {
        }
        roomsList.getChildren().add(root);
    }

    @FXML
    private void createRoom() {
        System.out.println("Create '" + roomName.getText() + "'room!");
        roomName.setText("");
    }
}
