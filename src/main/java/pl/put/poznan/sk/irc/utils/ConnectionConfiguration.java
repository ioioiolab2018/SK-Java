package pl.put.poznan.sk.irc.utils;

public class ConnectionConfiguration {
    private String username;
    private String hostAddress;
    private int port;

    public ConnectionConfiguration() {
        this.port = 7000;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "{ " +
                "username: '" + username + '\'' +
                ", hostAddress: '" + hostAddress + '\'' +
                ", port: " + port +
                " }";
    }
}
