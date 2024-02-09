package server;

import client.ClientGUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Server implements ServerInterface{
    private List<ClientGUI> clientGUIList;
    private JTextArea log;
    private ServerWindow serverWindow;
    private boolean work;
    public Server(ServerWindow serverWindow) {
        clientGUIList = new ArrayList<>();
        this.serverWindow = serverWindow;
        work = false;
    }
    @Override
    public void startServer() {
        if (!work) {
            work = true;
            serverWindow.appendLog("Сервер запущен!");
        } else {
            serverWindow.appendLog("Сервер уже был запущен");
        }
    }

    @Override
    public void stopServer() {
        if (work) {
            work = false;
            while (!clientGUIList.isEmpty()) {
                disconnectUser(clientGUIList.get(clientGUIList.size()-1));
            }
            serverWindow.appendLog("Сервер остановлен!");
        } else {
            serverWindow.appendLog("Сервер уже был остановлен");
        }
    }

    @Override
    public void addUser(String name) {
        if (work) {
            // Создание нового клиентского интерфейса и добавление его в список
            ClientGUI clientGUI = new ClientGUI(this); // или каким-то другим способом создайте объект ClientGUI
            clientGUIList.add(clientGUI);
        }
    }

    @Override
    public void removeUser(String name) {
        if (work) {
            // Поиск и удаление клиентского интерфейса из списка
            for (ClientGUI clientGUI : clientGUIList) {
                if (clientGUI.getName().equals(name)) {
                    clientGUIList.remove(clientGUI);
                    disconnectUser(clientGUI);
                    break;
                }
            }
        }
    }

    @Override
    public void sendMessage(String message) {
        if (work) {
            // Отправка сообщения всем клиентским интерфейсам
            for (ClientGUI clientGUI : clientGUIList) {
                clientGUI.sendMessage(message);
            }
        }
    }
}
