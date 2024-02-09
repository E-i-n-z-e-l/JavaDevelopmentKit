package client;

import server.Server;

public class Client {
    private View view;
    private String name;
    private Server server;
    private boolean connected;

    public Client(View view, Server server) {
        this.view = view;
        this.server = server;
    }

    /**
     * Метод "connectToServer" пытается подключиться к серверу с указанным именем.
     * Если подключение успешно, вызывается метод "showOnWindow" с сообщением об успешном
     * подключении и полученной историей сообщений с сервера (если она есть).
     * Возвращается значение "true". В случае неудачи также вызывается метод "showOnWindow"
     * с сообщением о неуспешном подключении. Возвращается значение "false".
     * @param name
     * @return
     */
    public boolean connectToServer(String name){
        this.name = name;
        if (server.connectUser(this)){
            showOnWindow("Вы успешно подключились!\n");
            connected = true;
            String log = server.getHistory();
            if (log != null){
                showOnWindow(log);
            }
            return true;
        } else {
            showOnWindow("Подключение не удалось");
            return false;
        }
    }

    /**
     * Метод "disconnectFromServer" проверяет, если клиент был подключен к серверу, то вызывается
     * метод "disconnectUser" у объекта "server", метод "disconnectedFromServer" у объекта "view"
     * и вызывается метод "showOnWindow" с сообщением об отключении от сервера. Затем значение поля
     * "connected" устанавливается в "false".
     */
    public void disconnectFromServer(){
        if (connected) {
            connected = false;
            server.disconnectUser(this);
            view.disconnectedFromServer();
            showOnWindow("Вы были отключены от сервера!");
        }
    }

    /**
     * Метод "answerFromServer" отображает сообщение от сервера, вызывая метод "showOnWindow" с указанным сообщением.
     * @param messageFromServer
     */
    public void answerFromServer(String messageFromServer){
        showOnWindow(messageFromServer);
    }

    /**
     * Метод "sendMessage" отправляет сообщение на сервер с указанием имени клиента и сообщения,
     * если клиент подключен к серверу. Если сообщение пустое или клиент не подключен, вызывается
     * метод "showOnWindow" с соответствующим сообщением.
     * @param message
     */
    public void sendMessage(String message){
        if (connected) {
            if (!message.isEmpty()) {
                server.sendMessage(name + ": " + message);
            }
        } else {
            showOnWindow("Нет подключения к серверу");
        }
    }

    /**
     * Метод "showOnWindow" отображает переданный текст, добавляя символ новой строки,
     * вызывая метод "sendMessage" у объекта "view".
     * @param text
     */
    private void showOnWindow(String text) {
        view.sendMessage(text + "\n");
    }
}
