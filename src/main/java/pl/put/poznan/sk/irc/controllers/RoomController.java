package pl.put.poznan.sk.irc.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pl.put.poznan.sk.irc.IRC;

public class RoomController {
    @FXML
    private Label icon;
    @FXML
    private Label roomName;
    @FXML
    private Label usersQuantity;

    @FXML
    void initialize() {
    }

    public void setUserInformation(String name, String quantity) {
        icon.setText(name.substring(0, 1));
        roomName.setText(name);
        usersQuantity.setText(quantity);
    }

    @FXML
    private void connectToRoom() {
        IRC.connectionGod.setRoomId(roomName.getText());
    }
}
