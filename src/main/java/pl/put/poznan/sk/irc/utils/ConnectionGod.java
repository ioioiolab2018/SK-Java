package pl.put.poznan.sk.irc.utils;

import javafx.application.Platform;
import pl.put.poznan.sk.irc.IRC;
import pl.put.poznan.sk.irc.model.Message;
import pl.put.poznan.sk.irc.model.Room;
import pl.put.poznan.sk.irc.model.User;

import java.io.*;
import java.net.Socket;

public class ConnectionGod implements Runnable {
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
            while (socket.isConnected()) {
                buffer = new byte[100];

                is.read(buffer);

                message += new String(buffer);

                if (message.contains("#")) {
                    System.out.println(message);
                    startIndex = message.indexOf("$");
                    endIndex = message.indexOf("#");
                    commandValue = message.substring(startIndex + 1, endIndex);

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
                                Platform.runLater(() -> IRC.connectionManager.setConnected(false));
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
                            if (IRC.connectionManager.getRoomController() != null) {
                                String[] rooms = commandValues[1].split(" ");
                                Platform.runLater(() -> {
                                    IRC.connectionManager.getRoomController().clearRoomsList();
                                    for (String room : rooms) {
                                        IRC.connectionManager.getRoomController()
                                                .displayRoom(new Room(room.split(";")));
                                    }
                                });
                            }
                            break;
                        case "users":
                            if (IRC.connectionManager.getUserController() != null) {
                                String[] users = commandValues[1].split(" ");
                                Platform.runLater(() -> {
                                    for (String user : users) {
                                        IRC.connectionManager.getUserController().displayUser(new User(user));
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
            socket = new Socket(
                    IRC.connectionConfiguration.getHostAddress(),
                    IRC.connectionConfiguration.getPort());

            os = socket.getOutputStream();
            is = socket.getInputStream();
            System.out.println("CONNECTED");
        } catch (IOException e) {
            System.out.println("Coś nie pykło!");
            IRC.connectionManager.setDisplayConnectionErrorMessage(true);
        }
    }

    /**
     * Metoda logująca użytkownika
     */
    private void loginToServer() throws IOException {
        String string = "$login:" + IRC.connectionConfiguration.getUsername() + "#";
        System.out.println(string);
        os.write(string.getBytes());
    }

    /**
     * Metoda zamykająca połączenie z serwerem
     */
    public void logoutFromServer() {
        String string = "$logout#";
        System.out.println(string);
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
        System.out.println(string);
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
        System.out.println(string);
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
        System.out.println(string);
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
        System.out.println(string);
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
        System.out.println(string);
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
        System.out.println(string);
        try {
            os.write(string.getBytes());
        } catch (IOException ignored) {
        }
    }
}
