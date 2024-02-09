package client;

import server.ServerWindow;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame implements View{
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    private Client client;
    JTextArea log;
    JTextField tfIPAddress, tfPort, tfLogin, tfMessage;
    JPasswordField password;
    JButton btnLogin, btnSend;
    JPanel headerPanel;

    public ClientGUI(ServerWindow serverWindow){
        setting(serverWindow);
        createPanel();

        setVisible(true);
    }

    /**
     * Метод "setting" устанавливает размер окна, его заголовок и расположение относительно "server".
     * @param server
     */
    private void setting(ServerWindow server) {
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        setLocation(server.getX() - 500, server.getY());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        client = new Client(this, server.getConnection());
    }

    /**
     * Метод "hideHeaderPanel" скрывает или показывает панель заголовка.
     * @param visible
     */
    private void hideHeaderPanel(boolean visible){
        headerPanel.setVisible(visible);
    }

    /**
     * Метод "sendMessage" отправляет сообщение, введенное в текстовое поле,
     * через клиентский объект "client", а затем очищает поле.
     */
    public void sendMessage(){
        client.sendMessage(tfMessage.getText());
        tfMessage.setText("");
    }

    /**
     * Метод "createPanel" добавляет созданные компоненты в окно.
     */
    private void createPanel() {
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createLog());
        add(createFooter(), BorderLayout.SOUTH);
    }

    /**
     * Метод "createHeaderPanel" создает панель заголовка с текстовыми полями для адреса
     * сервера, порта, логина, пароля и кнопкой "login". При нажатии на кнопку "login"
     * вызывается метод "connectedToServer".
     * @return
     */
    private Component createHeaderPanel(){
        headerPanel = new JPanel(new GridLayout(2, 3));
        tfIPAddress = new JTextField("127.0.0.1");
        tfPort = new JTextField("8189");
        tfLogin = new JTextField("Ivan Ivanovich");
        password = new JPasswordField("123456");
        btnLogin = new JButton("login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectedToServer();
            }
        });

        headerPanel.add(tfIPAddress);
        headerPanel.add(tfPort);
        headerPanel.add(new JPanel());
        headerPanel.add(tfLogin);
        headerPanel.add(password);
        headerPanel.add(btnLogin);

        return headerPanel;
    }

    /**
     * Метод "createLog" создает JTextArea для вывода лога сообщений. Он делается
     * нередактируемым и оборачивается в JScrollPane для добавления полос прокрутки.
     * @return
     */
    private Component createLog(){
        log = new JTextArea();
        log.setEditable(false);
        return new JScrollPane(log);
    }

    /**
     * Метод "createFooter" создает панель с текстовым полем для ввода сообщения и кнопкой "send".
     * При нажатии на кнопку "send" или нажатии клавиши Enter в текстовом поле вызывается метод "sendMessage".
     * @return
     */
    private Component createFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        tfMessage = new JTextField();
        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n'){
                    sendMessage();
                }
            }
        });
        btnSend = new JButton("send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        panel.add(tfMessage);
        panel.add(btnSend, BorderLayout.EAST);
        return panel;
    }

    /**
     * Метод "processWindowEvent" переопределен для обработки события закрытия окна.
     * При закрытии окна вызывается метод "disconnectedFromServer".
     * @param e  the window event
     */
    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING){
            disconnectedFromServer();
        }
    }

    /**
     * Метод "sendMessage" добавляет сообщение в лог пользователя.
     * @param message
     */
    @Override
    public void sendMessage(String message) {
        log.append(message);
    }

    /**
     * Метод "connectedToServer" пытается подключиться к серверу с указанным логином.
     * Если подключение успешно, панель заголовка скрывается.
     */
    @Override
    public void connectedToServer() {
        if (client.connectToServer(tfLogin.getText())){
            hideHeaderPanel(false);
        }
    }

    /**
     * Метод "disconnectedFromServer" скрывает панель заголовка и отключается от сервера.
     */
    @Override
    public void disconnectedFromServer() {
        hideHeaderPanel(true);
        client.disconnectFromServer();
    }
}
