package pl.put.poznan.sk.irc.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pl.put.poznan.sk.irc.IRC;

public class MessagesController {
    @FXML
    private Label roomName;
    @FXML
    private VBox messagesList;
    @FXML
    private AnchorPane information_container;

    @FXML
    void initialize() {
        roomName.setText(IRC.connectionGod.getRoomIdProperty().getValue());
    }

    @FXML
    private void disconnectFromRoom() {
        IRC.connectionGod.setRoomId(null);
    }

    @FXML
    private void sendMessage(KeyEvent keyEvent) {
    }

    @FXML
    private void send() {
    }
}
