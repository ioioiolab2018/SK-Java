package pl.put.poznan.sk.irc.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pl.put.poznan.sk.irc.model.Message;

public class MessageController {
    @FXML
    private Label icon;
    @FXML
    private Label username;
    @FXML
    private Label sendDate;
    @FXML
    private Label message;

    @FXML
    void initialize() {
    }

    public void setMessage(Message message) {
        this.icon.setText(message.getUsername().substring(0, 1));
        this.username.setText(message.getUsername());
        this.sendDate.setText(message.getSentDate());
        this.message.setText(message.getMessage());
    }
}
