package pl.put.poznan.sk.irc.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class T3Controller {
    @FXML
    private VBox usersList;

    @FXML
    void initialize() {}

    //  ===============   INTERFACE FUNCTIONS   ===============

    private void setPanel(String path) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(path));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ignored) {
        }
        usersList.getChildren().add(root);
    }

    //  ===============   OTHERS   ===============

}
