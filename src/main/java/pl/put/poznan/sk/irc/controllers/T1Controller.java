package pl.put.poznan.sk.irc.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.put.poznan.sk.irc.IRC;
import pl.put.poznan.sk.irc.utils.Utils;

public class T1Controller {
    @FXML
    private Label errorMessage;
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
        IRC.connectionManager.setRoomController(null);
        IRC.connectionManager.setUserController(null);

        if (IRC.connectionConfiguration.getUsername() != null) {
            name_input.setText(IRC.connectionConfiguration.getUsername());
        }
        if (IRC.connectionConfiguration.getHostAddress() != null) {
            host_address_input.setText(IRC.connectionConfiguration.getHostAddress() + ":" + IRC.connectionConfiguration.getPort());
        }

        IRC.connectionManager.getConnectionErrorMessageProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                errorMessage.setText(newValue);
                errorMessage.setVisible(true);
            } else {
                errorMessage.setVisible(false);
            }
        });

        disconnectButton.disableProperty()
                .bind(IRC.connectionManager.getConnectedProperty().not());
        connectButton.disableProperty()
                .bind(IRC.connectionManager.getConnectedProperty()
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
        IRC.connectionManager.leaveRoom();
        IRC.connectionManager.logout();
    }

    @FXML
    private void connect() {
        if (!host_address_input.getText().matches("\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}|localhost|" +
                "\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}:\\d{4}|localhost:\\d{4}")) {
            host_address_input.setStyle("-fx-background-color: red , white , white;");
            host_address_input.setPromptText("Błędna wartość!");
            return;
        }
        IRC.connectionConfiguration.setUsername(
                Utils.deleteInvalidCharcters(name_input.getText().trim())
                        .replace(" ", "_"));
        String hostAddress = host_address_input.getText();
        if (hostAddress.contains(":")) {
            String[] address = hostAddress.split(":");
            IRC.connectionConfiguration.setHostAddress(address[0].trim());
            IRC.connectionConfiguration.setPort(Integer.parseInt(address[1].trim()));
        } else {
            IRC.connectionConfiguration.setHostAddress(hostAddress.trim());
        }
        IRC.connectionManager.connectToServer();
        clear();
    }
}
