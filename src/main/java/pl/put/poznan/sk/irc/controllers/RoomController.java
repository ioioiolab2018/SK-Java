package pl.put.poznan.sk.irc.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pl.put.poznan.sk.irc.IRC;
import pl.put.poznan.sk.irc.model.Room;

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

    public void setRoomInformation(Room room) {
        icon.setText(room.getRoomName().substring(0, 1));
        roomName.setText(room.getRoomName());
        usersQuantity.setText(room.getUserQuantity());
    }

    @FXML
    private void connectToRoom() {
        if (IRC.connectionManager.getRoomIdProperty().getValue() != null) {
            IRC.connectionManager.leaveRoom();
        }
        IRC.connectionManager.setRoomId(roomName.getText());
        IRC.connectionManager.enterToRoom();
    }
}
