package server;

public interface ServerInterface {
    void startServer();
    void stopServer();
    void addUser(String name);
    void removeUser(String name);
    void sendMessage(String message);
}
