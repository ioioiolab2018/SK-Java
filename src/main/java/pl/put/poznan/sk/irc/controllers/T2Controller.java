package pl.put.poznan.sk.irc.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import pl.put.poznan.sk.irc.IRC;
import pl.put.poznan.sk.irc.model.Room;

import java.io.IOException;

public class T2Controller {
    @FXML
    private VBox roomsList;
    @FXML
    private TextField roomName;

    @FXML
    void initialize() {
        IRC.connectionManager.setRoomController(this);
        IRC.connectionManager.getRoomsList();
    }

    public void displayRoom(Room room) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/roomTemplate.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ignored) {
        }
        RoomController controller = loader.getController();
        controller.setRoomInformation(room);
        roomsList.getChildren().add(root);
    }

    @FXML
    private void createRoom() {
        IRC.connectionManager.createRoom(roomName.getText());
        IRC.connectionManager.getRoomsList();
        roomName.setText("");
    }
}
