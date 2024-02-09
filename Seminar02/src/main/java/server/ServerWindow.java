package server;

import client.ClientGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ServerWindow extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final String LOG_PATH = "src/server/server/log.txt";
    private List<ClientGUI> clientGUIList; // Приватное поле "clientGUIList" (типа "List<ClientGUI>"),
                                           // которое хранит список клиентских интерфейсов, подключенных к серверу;
    private JButton btnStart, btnStop;
    private JTextArea log;
    private boolean work;

    public ServerWindow(){
        clientGUIList = new ArrayList<>();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);

        createPanel();

        setVisible(true);
    }

    /**
     * Метод "connectUser" добавляет клиентский интерфейс в список "clientGUIList",
     * если сервер работает, возвращает "true". Если сервер не работает, возвращает "false".
     * @param clientGUI
     * @return
     */
    public boolean connectUser(ClientGUI clientGUI){
        if (!work){
            return false;
        }
        clientGUIList.add(clientGUI);
        return true;
    }

    /**
     * Метод "getLog" возвращает содержимое файла журнала, считывая его через метод "readLog".
     * @return
     */
    public String getLog() {
        return readLog();
    }

    /**
     *  Метод "disconnectUser" удаляет клиентский интерфейс из списка "clientGUIList"
     *  и вызывает метод "disconnectFromServer" у данного клиентского интерфейса, если он не равен "null".
     * @param clientGUI
     */
    public void disconnectUser(ClientGUI clientGUI){
        clientGUIList.remove(clientGUI);
        if (clientGUI != null){
            clientGUI.disconnectedFromServer();
        }
    }

    /**
     * Метод "message" добавляет текст в лог, отправляет его всем клиентским
     * интерфейсам через метод "answer", и сохраняет в журнале через метод "saveInLog".
     * @param text
     */
    public void message(String text){
        if (!work){
            return;
        }
        appendLog(text);
        answerAll(text);
        saveInLog(text);
    }

    /**
     * Метод "answerAll" отправляет переданный текст всем клиентским интерфейсам через метод "answer".
     * @param text
     */
    private void answerAll(String text){
        for (ClientGUI clientGUI: clientGUIList){
            clientGUI.answer(text);
        }
    }

    /**
     * Метод "saveInLog" сохраняет переданный текст в файл журнала "log.txt" с помощью FileWriter.
     * @param text
     */
    private void saveInLog(String text){
        try (FileWriter writer = new FileWriter(LOG_PATH, true)){
            writer.write(text);
            writer.write("\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Метод "readLog" считывает содержимое файла журнала и возвращает его в виде строки.
     * @return
     */
    private String readLog(){
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH);){
            int c;
            while ((c = reader.read()) != -1){
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Метод "appendLog" добавляет переданный текст в JTextArea "log".
     * @param text
     */
    public void appendLog(String text){
        log.append(text + "\n");
    }

    /**
     * Метод "createPanel" создает компоненты интерфейса, включая JTextArea и кнопки "Start" и "Stop", и добавляет их в окно.
     */
    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (work){
                    appendLog("Сервер уже был запущен");
                } else {
                    work = true;
                    appendLog("Сервер запущен!");
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!work){
                    appendLog("Сервер уже был остановлен");
                } else {
                    work = false;
                    while (!clientGUIList.isEmpty()){
                        disconnectUser(clientGUIList.get(clientGUIList.size()-1));
                    }
                    appendLog("Сервер остановлен!");
                }
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }
}
