package pl.put.poznan.sk.irc.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserController {
    @FXML
    private Label icon;
    @FXML
    private Label username;

    @FXML
    void initialize() {
    }

    public void setUserInformation(String user) {
        icon.setText(user.substring(0, 1));
        username.setText(user);
    }
}
