package pl.put.poznan.sk.irc.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import pl.put.poznan.sk.irc.IRC;
import pl.put.poznan.sk.irc.model.User;

import java.io.IOException;

public class T3Controller {
    @FXML
    private VBox usersList;

    @FXML
    void initialize() {
        IRC.connectionManager.setUserController(this);
        IRC.connectionManager.getUsersList();
        IRC.connectionManager.setRoomController(null);
    }

    public void displayUser(User user) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/userTemplate.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ignored) {
        }
        UserController controller = loader.getController();
        controller.setUserInformation(user);
        usersList.getChildren().add(root);
    }
}
