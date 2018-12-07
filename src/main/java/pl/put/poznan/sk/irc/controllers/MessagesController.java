package pl.put.poznan.sk.irc.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import pl.put.poznan.sk.irc.IRC;
import pl.put.poznan.sk.irc.model.Message;

import java.io.IOException;
import java.time.LocalDateTime;

public class MessagesController {
    @FXML
    private ScrollPane messagesScroll;
    @FXML
    private TextArea messageInput;
    @FXML
    private Label roomName;
    @FXML
    private VBox messagesList;

    @FXML
    void initialize() {
        roomName.setText(IRC.connectionManager.getRoomIdProperty().getValue());
        for (int i = 0; i < 10; i++) {
            displayMessage(new Message("Tomek", "Cześć!", "16:20"));
        }
        displayMessage(new Message("Tomek", "Jestem Tomek. A Ty?", "16:20"));
        displayMessage("/fxml/messageTemplate2.fxml", new Message(IRC.connectionConfiguration.getUsername(), "Cześć Tomek...", "16:21"));
        displayMessage(new Message("Tomek", "No cześć.. xD", "16:21"));
    }

    private void displayMessage(String path, Message message) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(path));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ignored) {
        }
        MessageController messageController = loader.getController();
        messageController.setMessage(message);
        messagesList.getChildren().add(root);
        messagesScroll.setVvalue(1);
    }

    public void displayMessage(Message message) {
        displayMessage("/fxml/messageTemplate.fxml", message);
    }

    @FXML
    private void disconnectFromRoom() {
        IRC.connectionManager.setRoomId(null);
    }

    @FXML
    private void sendMessage(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            send();
        }
    }

    @FXML
    private void send() {
        LocalDateTime localDateTime = LocalDateTime.now();
        int minutes = localDateTime.getMinute();
        String dateString = localDateTime.getHour() + ":" + (minutes < 10 ? "0" + minutes : minutes);
        String text = messageInput.getText();
        if (!text.equals("") && !text.equals("\n")) {
            Message message = new Message(IRC.connectionConfiguration.getUsername(), text, dateString);
            displayMessage("/fxml/messageTemplate2.fxml", message);
        }
        messageInput.setText("");
    }
}
