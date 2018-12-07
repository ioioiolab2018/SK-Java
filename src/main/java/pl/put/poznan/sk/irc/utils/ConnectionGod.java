package pl.put.poznan.sk.irc.utils;

import pl.put.poznan.sk.irc.IRC;

import java.io.*;
import java.net.Socket;

public class ConnectionGod implements Runnable {
    private Socket socket;
    private InputStream is;
    private BufferedReader reader;
    private OutputStream os;

    public ConnectionGod() {
    }

    @Override
    public void run() {
        connectToServer();

        byte[] buffer = new byte[100];
        String commandValue;
        String message = "";
        int index;

        try {
            loginToServer();
            while (socket.isConnected()) {

                is.read(buffer);

                message += new String(buffer);

                if (message.contains("#")) {
                    index = message.indexOf("#");
                    commandValue = message.substring(0, index);
                    message = message.substring(index);
                    System.out.println("commandValue\n\t" + commandValue);

                    String[] commandValues = commandValue.split(":");
                    switch (commandValues[0]) {
                        case "login":
                            System.out.println("login:\n\t" + commandValues[1]);
//                            index = message.indexOf(" ");
//                            commandValue = message.substring(0, index);
//                            message = message.substring(index + 1);
//                            if (commandValue.equals("ok")) {
//                                IRC.connectionManager.setConnected(true);
//                            } else {
//                                IRC.connectionManager.setConnected(false);
//                                System.out.println(message);
//                            }
                            break;
                        case "logout":
                            System.out.println("logout:\n\t" + commandValues[1]);
//                            IRC.connectionManager.setConnected(false);
                            break;
                        case "message":
                            System.out.println("message:\n\t" + commandValues[1]);
                            break;
                        case "rooms":
                            System.out.println("rooms:\n\t" + commandValues[1]);
//                            index = message.indexOf(" ");
//                            commandValue = message.substring(0, index);
//                            message = message.substring(index + 1);
//                            if (commandValue.equals("ok")) {
//
//                            } else {
//                                System.out.println(message);
//                            }
                            break;
                        case "users":
                            System.out.println("users:\n\t" + commandValues[1]);
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
            reader = new BufferedReader(new InputStreamReader(is));
        } catch (IOException e) {
            IRC.connectionManager.setDisplayConnectionErrorMessage(true);
        }
    }

    /**
     * Metoda logująca użytkownika
     */
    private void loginToServer() throws IOException {
        String string = "login " + IRC.connectionConfiguration.getUsername();
        os.write(string.getBytes());
    }

    /**
     * Metoda zamykająca połączenie z serwerem
     */
    public void logoutFromServer() {
        String string = "logout";
        try {
            os.write(string.getBytes());
        } catch (IOException ignored) {
        }
    }

    /**
     * Metoda dodająca pokój
     */
    public void createRoom(String roomName) {
        String string = "create " + roomName;
        try {
            os.write(string.getBytes());
        } catch (IOException ignored) {
        }
    }

    /**
     * Metoda pozwalająca na dołączenie do pokoju
     */
    public void enterToRoom(String roomName) throws IOException {
        String string = "enter " + roomName;
        os.write(string.getBytes());
    }

    /**
     * Metoda pobierająca listę pokoi
     */
    public void getRoomsList() throws IOException {
        String string = "rooms";
        os.write(string.getBytes());
    }

    /**
     * Metoda pobierająca listę użytkowników w pokoju
     */
    public void getUsersList(String roomName) throws IOException {
        String string = "users " + roomName;
        os.write(string.getBytes());
    }
}
