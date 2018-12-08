package pl.put.poznan.sk.irc.utils;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.put.poznan.sk.irc.IRC;
import pl.put.poznan.sk.irc.controllers.MessagesController;
import pl.put.poznan.sk.irc.controllers.T2Controller;
import pl.put.poznan.sk.irc.controllers.T3Controller;
import pl.put.poznan.sk.irc.model.Message;

public class ConnectionManager {
    private ConnectionGod connectionGod;
    private BooleanProperty Connected = new SimpleBooleanProperty(false);
    private StringProperty roomId = new SimpleStringProperty(null);
    private BooleanProperty displayConnectionErrorMessage = new SimpleBooleanProperty(false);
    private MessagesController messagesController;
    private T2Controller roomController;
    private T3Controller userController;

    public ConnectionManager() {
    }

    public BooleanProperty getConnectedProperty() {
        return Connected;
    }

    public void setConnected(boolean connected) {
        this.Connected.set(connected);
    }

    public StringProperty getRoomIdProperty() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId.set(roomId);
    }

    public BooleanProperty isDisplayConnectionErrorMessageProperty() {
        return displayConnectionErrorMessage;
    }

    public void setDisplayConnectionErrorMessage(boolean displayConnectionErrorMessage) {
        this.displayConnectionErrorMessage.set(displayConnectionErrorMessage);
    }

    public MessagesController getMessagesController() {
        return messagesController;
    }

    public void setMessagesController(MessagesController messagesController) {
        this.messagesController = messagesController;
    }

    public T2Controller getRoomController() {
        return roomController;
    }

    public void setRoomController(T2Controller roomController) {
        this.roomController = roomController;
    }

    public T3Controller getUserController() {
        return userController;
    }

    public void setUserController(T3Controller userController) {
        this.userController = userController;
    }

    public void connectToServer() {
        connectionGod = new ConnectionGod();
        new Thread(connectionGod).start();
    }

    public void logout() {
        connectionGod.logoutFromServer();
        IRC.connectionManager.setRoomId(null);
    }

    public void createRoom(String roomName) {
        connectionGod.createRoom(roomName);
    }

    public void getRoomsList() {
        connectionGod.getRoomsList();
    }

    public void getUsersList() {
        connectionGod.getUsersList(roomId.getValue());
    }

    public void enterToRoom() {
        connectionGod.enterToRoom(roomId.getValue());
    }

    public void leaveRoom() {
        if (roomId.getValue() != null) {
            connectionGod.leaveRoom(roomId.getValue());
        }
    }

    public void sendMessage(Message message) {
        connectionGod.sendMessage(message);
    }
}
