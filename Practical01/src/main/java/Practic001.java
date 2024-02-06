import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Practic001 extends JFrame {
    JButton btnStart = new JButton("Сервер запущен");
    JButton btnNotPress = new JButton("Сервер остановлен");
    JTextArea logTextArea = new JTextArea(); // Создаем поле лога в котором выводится текст;
    JPanel mainPanel = new JPanel(new CardLayout()); // Создаем панель, на которой будут размещаться компоненты;
    private boolean isServerWorking;
    List<User> users = new ArrayList<>(); // Создаем список пользователей чата;
    public Practic001() {
        super("Practic001"); // Название окошка;

        setDefaultCloseOperation(EXIT_ON_CLOSE); // Закрывая окошко, закрываешь программу;

        setSize(800, 600); // Размеры окошка;
        setLocation(600, 200); // Расположение окна на экране;

        mainPanel.setLayout(new GridLayout(1, 2)); // Задаем GridLayout для основной панели;

        isServerWorking = false;
        logTextArea.setEditable(false); // Запрещаем писать в поле лога;

        // Создаем слушателя событий для кнопки btnStart:
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    logTextArea.append("Сервер уже запущен\n");
                } else {
                    isServerWorking = true;
                    logTextArea.append("Сервер запущен\n");
                }
                restoreLogFromFile("log.txt");
                notifyUsers();
            }
        });


        // Создаем слушателя событий для кнопки btnNotPress:
        btnNotPress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    isServerWorking = false;
                    logTextArea.append("Сервер остановлен\n");
                } else {
                    logTextArea.append("Сервер не был запущен\n");
                }
            }
        });

        // Добавляем компоненты на основную панель:
        mainPanel.add(btnStart, "buttons");
        mainPanel.add(btnNotPress, "buttons");

        JPanel logPanel = new JPanel(new BorderLayout()); // Создаем панель для поля лога;
        logPanel.add(new JScrollPane(logTextArea), BorderLayout.CENTER); // Добавляем поле лога с прокруткой;

        getContentPane().add(mainPanel, BorderLayout.SOUTH); // Добавляем основную панель в верхнюю часть окна;
        getContentPane().add(logPanel, BorderLayout.CENTER); // Добавляем панель с полем лога в центр окна;

        setVisible(true); // Делаем окошко видимым;
    }
    public void addMessageToLog(String message) {
        logTextArea.append(message);
    }

    // Метод для проверки состояния сервера:
    public boolean isServerRunning() {
        return isServerWorking;
    }
    // Метод для уведомления всех пользователей о состоянии сервера
    public void notifyUsers() {
        String message;
        if (isServerWorking) {
            message = "Вы успешно подключились к серверу\n";
        } else {
            message = "Не удалось подключиться к серверу\n";
        }
        for (User user : users) {
            user.addMessageToLog(message);
        }
    }
    public void addUser(User user) {
        users.add(user);
    }
    public void saveLogToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(logTextArea.getText());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void restoreLogFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logTextArea.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
