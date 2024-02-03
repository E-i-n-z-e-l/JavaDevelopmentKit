import javax.swing.*;
import java.awt.*;

public class ClientGUI extends JFrame {
    private static final int WIDTH = 400; // Устанавливаем размеры окна;
    private static final int HEIGHT = 300;
    private final JTextArea log = new JTextArea(); // Создаем окно для вывода логов клиента;
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3)); // Создаем панель для вывода информации;
    private final JTextField tfIPAdress = new JTextField("127.0.0.1"); // Поле ввода текста;
    private final JTextField tfPort = new JTextField("8189"); // Поле ввода текста;
    private final JTextField tfLogin = new JTextField("Aleksey"); // Поле ввода текста;
    private final JPasswordField tfPassword = new JPasswordField("12345"); // Поле ввода пароля;
    private final JButton btnLogin = new JButton("Login"); // Кнопка предназначенная для входя пользователя;
    private final JPanel panelBottom = new JPanel(new BorderLayout());// Панель, на которой будут располагаться компоненты в нижней части окна
    private final JTextField tfMessage = new JTextField(); // Это поле ввода текста, предназначенное для ввода сообщений пользователем;
    private final JButton btnSend = new JButton("Send"); // Кнопка, предназначенная для отправки сообщений;
    private final JList<String> userList = new JList<>(); // Список пользователей;
    ClientGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Закрываешь окно - закрываешь программу;
        setLocationRelativeTo(null); // Вызов окна по центру экрана;
        setSize(WIDTH, HEIGHT); // Устанавливаем размер окна;
        setTitle("Chat Client"); // Названия окна;

        panelTop.add(tfIPAdress);  // Добавляем на панель кнопки и располагаем их на ней;
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        add(panelTop, BorderLayout.NORTH);

        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        log.setEditable(false); // Запрещаем пользователю редактировать текст в log;
        /*JScrollPane создается для компонента log, чтобы добавить прокрутку,
        если содержимое log не помещается полностью на экране*/
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);

        DefaultListModel<String> userModel = new DefaultListModel<>();
        userModel.addElement("User1"); // Добавьте имитацию имен пользователей
        userModel.addElement("User2");
        userModel.addElement("User3");
        userModel.addElement("User4");
        userList.setModel(userModel);
        add(new JScrollPane(userList), BorderLayout.WEST);

        setVisible(true); // Делаем окно видимым;
    }
}
