package pl.put.poznan.sk.irc.utils;

import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sk.irc.IRC;
import pl.put.poznan.sk.irc.model.Message;
import pl.put.poznan.sk.irc.model.Room;
import pl.put.poznan.sk.irc.model.User;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectionGod implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionGod.class);
    private Socket socket;
    private InputStream is;
    private OutputStream os;

    public ConnectionGod() {
    }

    @Override
    public void run() {
        connectToServer();

        byte[] buffer;
        String commandValue;
        String message = "";
        int startIndex, endIndex;

        try {
            loginToServer();
            while (socket != null && socket.isConnected()) {
                buffer = new byte[100];

                is.read(buffer);

                message += new String(buffer);

                while (message.contains("#")) {
                    startIndex = message.indexOf("$");
                    endIndex = message.indexOf("#");
                    commandValue = message.substring(startIndex + 1, endIndex);
                    logger.info(commandValue);

                    if (message.indexOf("$", endIndex) > endIndex) {
                        message = message.substring(endIndex + 1);
                    } else {
                        message = "";
                    }

                    String[] commandValues = commandValue.split(":");
                    switch (commandValues[0]) {
                        case "login":
                            if (commandValues[1].equals("ok")) {
                                Platform.runLater(() -> IRC.connectionManager.setConnected(true));
                            } else {
                                Platform.runLater(() -> IRC.connectionManager.setConnectionErrorMessage("Wystąpił błąd podczas logowania lub istnieje już użytkownik o tej samej nazwie!"));
                                logger.error("Wystąpił błąd podczas logowania lub istnieje już użytkownik o tej samej nazwie!");
                            }
                            break;
                        case "logout":
                            Platform.runLater(() -> IRC.connectionManager.setConnected(false));
                            socket.close();
                            return;
                        case "message":
                            if (IRC.connectionManager.getMessagesController() != null) {
                                Platform.runLater(() -> IRC.connectionManager.getMessagesController()
                                        .displayMessage(new Message(commandValues[1].split(";"))));
                            }
                            break;
                        case "create":
                            if (commandValues[1].equals("ok")) {
                                getRoomsList();
                            }
                            break;
                        case "enter":
                            if (commandValues[1].equals("ok")) {
                                Platform.runLater(() -> IRC.connectionManager.setRoomId(IRC.connectionManager.getRequestedRoomId()));
                            }
                            break;
                        case "leave":
                            if (commandValues[1].equals("ok")) {
                                Platform.runLater(() -> IRC.connectionManager.setRoomId(null));
                            }
                            break;
                        case "rooms":
                            if (commandValues.length > 1) {
                                String[] rooms = commandValues[1].split(" ");
                                Platform.runLater(() -> {
                                    if (IRC.connectionManager.getRoomController() != null) {
                                        IRC.connectionManager.getRoomController().clearRoomsList();
                                        for (String room : rooms) {
                                            IRC.connectionManager.getRoomController()
                                                    .displayRoom(new Room(room.split(";")));
                                        }
                                    }
                                });
                            } else {
                                Platform.runLater(() -> {
                                    if (IRC.connectionManager.getRoomController() != null) {
                                        IRC.connectionManager.getRoomController().clearRoomsList();
                                    }
                                });
                            }
                            break;
                        case "users":
                            if (IRC.connectionManager.getUserController() != null && commandValues.length > 1) {
                                String[] users = commandValues[1].split(" ");
                                Platform.runLater(() -> {
                                    if (IRC.connectionManager.getUserController() != null) {
                                        IRC.connectionManager.getUserController().clearUsersList();
                                        for (String user : users) {
                                            IRC.connectionManager.getUserController().displayUser(new User(user));
                                        }
                                    }
                                });
                            }
                            break;
                    }
                }
            }
        } catch (IOException ignored) {
        }
    }

    /**
     * Metoda tworząca połączenie z serwerem
     */
    private void connectToServer() {
        try {
            socket = new Socket();
            socket.connect(
                    new InetSocketAddress(
                            IRC.connectionConfiguration.getHostAddress(),
                            IRC.connectionConfiguration.getPort()),
                    1000);

            os = socket.getOutputStream();
            is = socket.getInputStream();
            logger.info("Connected to server!");
        } catch (IOException e) {
            Platform.runLater(() -> IRC.connectionManager.setConnectionErrorMessage("Nie można nawiązać połączenia z serwerem!"));
            logger.error("Nie można nawiązać połączenia z serwerem!");
        }
    }

    /**
     * Metoda logująca użytkownika
     */
    private void loginToServer() {
        String string = "$login:" + IRC.connectionConfiguration.getUsername() + "#";
        logger.info(string);
        try {
            os.write(string.getBytes());
        } catch (Exception ignored) {
        }
    }

    /**
     * Metoda zamykająca połączenie z serwerem
     */
    public void logoutFromServer() {
        String string = "$logout#";
        logger.info(string);
        try {
            os.write(string.getBytes());
        } catch (IOException ignored) {
        }
    }

    /**
     * Metoda dodająca pokój
     */
    public void createRoom(String roomName) {
        String string = "$create:" + roomName + "#";
        logger.info(string);
        try {
            os.write(string.getBytes());
        } catch (IOException ignored) {
        }
    }

    /**
     * Metoda pozwalająca na dołączenie do pokoju
     */
    public void enterToRoom(String roomName) {
        String string = "$enter:" + roomName + "#";
        logger.info(string);
        try {
            os.write(string.getBytes());
        } catch (IOException ignored) {
        }
    }

    /**
     * Metoda pozwalająca na opuszczenie pokoju
     */
    public void leaveRoom(String roomName) {
        String string = "$leave:" + roomName + "#";
        logger.info(string);
        try {
            os.write(string.getBytes());
        } catch (IOException ignored) {
        }
    }

    /**
     * Metoda pobierająca listę pokoi
     */
    public void getRoomsList() {
        String string = "$rooms#";
        logger.info(string);
        try {
            os.write(string.getBytes());
        } catch (IOException ignored) {
        }
    }

    /**
     * Metoda pobierająca listę użytkowników w pokoju
     */
    public void getUsersList(String roomName) {
        String string = "$users:" + roomName + "#";
        logger.info(string);
        try {
            os.write(string.getBytes());
        } catch (IOException ignored) {
        }
    }

    /**
     * Metoda wysyłająca wiadomość do innych użytkowników
     */
    public void sendMessage(Message message) {
        String string = "$message:"
                + message.getUsername()
                + ";" + message.getMessage()
                + ";" + message.getSentDate().replace(":", "-")
                + "#";
        logger.info(string);
        try {
            os.write(string.getBytes());
        } catch (IOException ignored) {
        }
    }

    public void closeSocket() {
        if (socket != null) {
            try {
                socket.close();
                socket = null;
                logger.info("Socket closed!");
            } catch (IOException ignored) {
            }
        }
    }
}
