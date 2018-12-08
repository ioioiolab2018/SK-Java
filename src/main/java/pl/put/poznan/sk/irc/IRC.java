package pl.put.poznan.sk.irc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import pl.put.poznan.sk.irc.utils.ConnectionConfiguration;
import pl.put.poznan.sk.irc.utils.ConnectionManager;

public class IRC extends Application {
    public static ConnectionManager connectionManager;
    public static ConnectionConfiguration connectionConfiguration;

    private static final String MAIN_ICON = "/images/logo.png";

    @Override
    public void start(Stage primaryStage) throws Exception{
        connectionManager = new ConnectionManager();
        connectionConfiguration = new ConnectionConfiguration();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("SK-IRC");
        primaryStage.setScene(new Scene(root, 1200, 720));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(MAIN_ICON));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop(){
        connectionManager.leaveRoom();
        connectionManager.logout();
    }
}
