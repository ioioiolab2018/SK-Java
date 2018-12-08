package pl.put.poznan.sk.irc.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pl.put.poznan.sk.irc.model.User;

public class UserController {
    @FXML
    private Label icon;
    @FXML
    private Label username;

    @FXML
    void initialize() {
    }

    public void setUserInformation(User user) {
        icon.setText(user.getUsername().substring(0, 1));
        username.setText(user.getUsername());
    }
}
