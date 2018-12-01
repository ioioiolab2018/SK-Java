package pl.put.poznan.sk.irc.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class T3Controller {
    @FXML
    private TextField node_input;
    @FXML
    private VBox function_panel;

    @FXML
    void initialize() {}

    //  ===============   INTERFACE FUNCTIONS   ===============
    public void clear() {
        node_input.setStyle("-fx-background-color: #a9a9a9 , white , white;");
        node_input.setPromptText("");
    }

    private void setPanel(String path) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(path));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ignored) {
        }
        function_panel.getChildren().add(root);
    }

    //  ===============   OTHERS   ===============

}
