package server;

import client.ClientGUI;

public interface ServerView {
    void connectUser(ClientGUI clientGUI);
    void disconnectUser(ClientGUI clientGUI);
    void message(String text);
    String getLog();
}
