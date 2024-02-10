package server;

import client.ClientGUI;

import java.util.ArrayList;
import java.util.List;

public class Server implements ServerView {
    private List<ClientGUI> clientGUIList;
    private boolean work;
    private String log;

    public Server() {
        clientGUIList = new ArrayList<>();
        work = false;
        log = "";
    }

    @Override
    public void connectUser(ClientGUI clientGUI) {
        if (work) {
            clientGUIList.add(clientGUI);
        }
    }

    @Override
    public void disconnectUser(ClientGUI clientGUI) {
        clientGUIList.remove(clientGUI);
    }

    @Override
    public void message(String text) {
        if (work) {
            log += text + "\n";
            answerAll(text);
        }
    }

    @Override
    public String getLog() {
        return log;
    }

    private void answerAll(String text) {
        for (ClientGUI clientGUI : clientGUIList) {
            clientGUI.answerFromServer(text);
        }
    }

    public void startServer() {
        if (!work) {
            work = true;
            log = "Server started!\n";
        }
    }

    public void stopServer() {
        if (work) {
            work = false;
            log = "Server stopped!\n";
            clientGUIList.clear();
        }
    }

    public boolean isServerRunning() {
        return work;
    }
}
