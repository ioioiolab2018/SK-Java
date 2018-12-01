package pl.put.poznan.sk.irc.model;

public class Message {
    private String username;
    private String message;
    private String sentDate;

    public Message() {
    }

    public Message(String username, String message, String sentDate) {
        this.username = username;
        this.message = message;
        this.sentDate = sentDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }
}
