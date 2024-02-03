import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ServerWindow extends JFrame {
    // Создаем окно;
    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int POS_WIDTH = 400;
    private static final int POS_HEIGHT = 300;
    // Создаем кнопки;
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();
    // JTextArea - компонент, предназначенный для отображения и редактирования многострочного текста.
    // В нашем случае, мы можем использовать это поле для вывода логов сервера;
    private JScrollPane scrollPane = new JScrollPane(log);
    private boolean isServerWorking;
    // Переменная isServerWorking, которая будет хранить значение true или false
    // в зависимости от того, работает ли сервер или нет;

    public ServerWindow() {
        isServerWorking = false; // Изначально сервер выключен;
        btnStop.addActionListener(new ActionListener() { // Процесс выключения сервера;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    isServerWorking = false;
                    log.append("Server Stopped\n"); // Вывод сообщения в JTextArea
                } else {
                    log.append("Server is not running\n");
                }
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // Процесс включения сервера;
                if (isServerWorking) {
                    log.append("Server is already running\n");
                } else {
                    isServerWorking = true;
                    log.append("Server Started\n");
                }
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Закрываешь окно - закрываешь программу;
        setBounds(POS_X, POS_Y, POS_WIDTH, POS_HEIGHT); // Определяем положение и размеры окна;
        setResizable(false); // Запрет на изменение размеров окна;
        setTitle("Chat Server"); // Название окна;
        setAlwaysOnTop(true); // Этот метод указывает, что окно должно быть всегда наверху, даже если пользователь активирует другие окна;

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnStart);
        buttonPanel.add(btnStop);

        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.add(new JScrollPane(log), BorderLayout.CENTER);

        //setLayout(new GridLayout(1, 2));
        /* Этот метод устанавливает менеджер компоновки окна. В данном случае используется GridLayout с
        одной строкой и двумя столбцами. Это означает, что кнопки btnStart и btnStop будут размещены в окне
        в одной строке, в двух столбцах.*/
        add(logPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true); // Определяет видимость окна;
        log.setEditable(false); // Запрет редактирования текста пользователем
        add(scrollPane); // Добавление scrollPane на окно
    }

}
