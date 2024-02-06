import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User extends JFrame {
    JButton btnSend = new JButton("Отправить сообщение");
    JTextArea logTextArea = new JTextArea(); // Создаем поле лога в котором выводится текст;

    JTextField inputTextField = new JTextField();
    JTextField inputNameUser = new JTextField("Username");
    JPanel mainPanel = new JPanel(new CardLayout()); // Создаем панель, на которой будут размещаться компоненты;
    private Practic001 server;
    public User (Practic001 server) {
        super("User"); // Название окошка;

        this.server = server;
        server.addUser(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE); // Закрывая окошко, закрываешь программу;
        setSize(400, 600); // Размеры окошка;
        setLocation(1400, 200); // Расположение окна на экране;
        logTextArea.setEditable(false); // Запрещаем писать в поле лога;



        mainPanel.setLayout(new BorderLayout());
        // Добавляем поле ввода и кнопку на основную панель:
        mainPanel.add(inputTextField, BorderLayout.NORTH);
        mainPanel.add(btnSend, BorderLayout.SOUTH);
        mainPanel.add(inputNameUser, BorderLayout.EAST);

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Получаем текст из JTextField inputTextField:
                String message = inputTextField.getText();
                String name = inputNameUser.getText();
                // Отправляем сообщение в лог сервера, только если сервер запущен:
                if (server.isServerRunning()) {
                    server.addMessageToLog(name + ": " + message + "\n");
                    logTextArea.append(name + ": " + message + "\n");
                    server.saveLogToFile("log.txt");
                } else {
                    logTextArea.append("Не удалось отправить сообщение. Сервер не запущен\n");
                }
                // Очищаем JTextField после отправки сообщения:
                inputTextField.setText("");
            }
        });

        JPanel logPanel = new JPanel(new BorderLayout()); // Создаем панель для поля лога
        logPanel.add(new JScrollPane(logTextArea), BorderLayout.CENTER); // Добавляем поле лога с прокруткой;

        getContentPane().add(mainPanel, BorderLayout.SOUTH); // Добавляем основную панель в верхнюю часть окна;
        getContentPane().add(logPanel, BorderLayout.CENTER); // Добавляем панель с полем лога в центр окна;

        setVisible(true); // Делаем окошко видимым;
    }
    public void addMessageToLog(String message) {
        if (server.isServerRunning()) {
            logTextArea.append(message);
        } else {
            logTextArea.append("Не удалось отправить сообщение. Сервер не запущен\n");
        }
    }
}
