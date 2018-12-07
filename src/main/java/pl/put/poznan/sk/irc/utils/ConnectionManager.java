package pl.put.poznan.sk.irc.utils;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConnectionManager {
    private ConnectionGod connectionGod;
    private BooleanProperty Connected = new SimpleBooleanProperty(false);
    private StringProperty roomId = new SimpleStringProperty(null);
    private BooleanProperty displayConnectionErrorMessage = new SimpleBooleanProperty(false);

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

    public void connectToServer() {
        connectionGod = new ConnectionGod();
        new Thread(connectionGod).start();
    }

    public void logout() {
        connectionGod.logoutFromServer();
    }

    public void createRoom(String roomName) {
        connectionGod.createRoom(roomName);
    }


}
