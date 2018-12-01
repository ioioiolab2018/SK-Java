package pl.put.poznan.sk.irc;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConnectionGod {
    private BooleanProperty Connected = new SimpleBooleanProperty(false);
    private StringProperty roomId = new SimpleStringProperty(null);
    private String username;
    private String hostAddress;

    public ConnectionGod() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }
}
