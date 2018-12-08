package pl.put.poznan.sk.irc.model;

public class Room {
    private String roomName;
    private String userQuantity;

    public Room() {
    }

    public Room(String roomName, String userQuantity) {
        this.roomName = roomName;
        this.userQuantity = userQuantity;
    }

    public Room(String[] roomArray) {
        this.roomName = roomArray[0];
        this.userQuantity = roomArray[1];
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getUserQuantity() {
        return userQuantity;
    }

    public void setUserQuantity(String userQuantity) {
        this.userQuantity = userQuantity;
    }
}
