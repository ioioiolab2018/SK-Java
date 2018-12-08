package pl.put.poznan.sk.irc.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pl.put.poznan.sk.irc.IRC;

import java.io.IOException;

public class MainController {
    @FXML
    private BorderPane mainContainer;
    @FXML
    private VBox main_panel;
    @FXML
    private ToggleButton t1Button;
    @FXML
    private ToggleButton t2Button;
    @FXML
    private ToggleButton t3Button;
    @FXML
    private ToggleGroup optionGroup;

    @FXML
    void initialize() {
        setPanel("/fxml/T1.fxml");
        setMainContainer("/fxml/welcome.fxml");
        t2Button.disableProperty().bind(IRC.connectionManager.getConnectedProperty().not());
        t3Button.disableProperty().bind(IRC.connectionManager.getRoomIdProperty().isNull());
        IRC.connectionManager.getConnectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                optionGroup.selectToggle(optionGroup.getToggles().get(1));
                displayT2();
            } else {
                optionGroup.selectToggle(optionGroup.getToggles().get(0));
                displayT1();
            }
        });
        IRC.connectionManager.getRoomIdProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                optionGroup.selectToggle(optionGroup.getToggles().get(2));
                displayT3();
                setMainContainer("/fxml/messages.fxml");
            } else {
                if (IRC.connectionManager.getConnectedProperty().getValue()) {
                    optionGroup.selectToggle(optionGroup.getToggles().get(1));
                    displayT2();
                } else {
                    optionGroup.selectToggle(optionGroup.getToggles().get(0));
                    displayT1();
                }
                setMainContainer("/fxml/welcome.fxml");
            }
        });
    }

    private void setPanel(String path) {
        main_panel.getChildren().removeAll(main_panel.getChildren());
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(path));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ignored) {
        }
        main_panel.getChildren().add(root);
    }

    private void setMainContainer(String path) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(path));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ignored) {
        }
        mainContainer.setCenter(root);
    }

    @FXML
    private void displayT1() {
        if (optionGroup.getSelectedToggle() == null) {
            t1Button.setSelected(true);
        } else {
            setPanel("/fxml/T1.fxml");
        }
    }

    @FXML
    private void displayT2() {
        if (optionGroup.getSelectedToggle() == null) {
            t2Button.setSelected(true);
        } else {
            setPanel("/fxml/T2.fxml");
        }
    }

    @FXML
    private void displayT3() {
        if (optionGroup.getSelectedToggle() == null) {
            t3Button.setSelected(true);
        } else {
            setPanel("/fxml/T3.fxml");
        }
    }
}
