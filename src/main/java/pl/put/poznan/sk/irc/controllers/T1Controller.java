package pl.put.poznan.sk.irc.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pl.put.poznan.sk.irc.IRC;

public class T1Controller {
    @FXML
    private TextField name_input;
    @FXML
    private TextField host_address_input;
    @FXML
    public Button connectButton;
    @FXML
    public Button disconnectButton;

    @FXML
    void initialize() {
        if (IRC.connectionGod.getUsername() != null) {
            name_input.setText(IRC.connectionGod.getUsername());
        }
        if (IRC.connectionGod.getHostAddress() != null) {
            host_address_input.setText(IRC.connectionGod.getHostAddress());
        }
        disconnectButton.disableProperty()
                .bind(IRC.connectionGod.getConnectedProperty().not());
        connectButton.disableProperty()
                .bind(IRC.connectionGod.getConnectedProperty()
                        .or(name_input.textProperty().isEmpty())
                        .or(host_address_input.textProperty().isEmpty()));
    }

    //  ===============   OTHERS   ===============
    private void clear() {
        name_input.setStyle("-fx-background-color: #a9a9a9 , white , white;");
        host_address_input.setStyle("-fx-background-color: #a9a9a9 , white , white;");
    }

    //  ===============   INTERFACE FUNCTIONS   ===============
    @FXML
    private void disconnect() {
        IRC.connectionGod.setConnected(false);
        IRC.connectionGod.setRoomId(null);
    }

    @FXML
    private void connect() {
        if (!host_address_input.getText().matches("\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}|localhost")) {
            host_address_input.setStyle("-fx-background-color: red , white , white;");
            host_address_input.setPromptText("Błędna wartość!");
            return;
        }
        IRC.connectionGod.setUsername(name_input.getText());
        IRC.connectionGod.setHostAddress(host_address_input.getText());
        IRC.connectionGod.setConnected(true);
        clear();
    }

}
