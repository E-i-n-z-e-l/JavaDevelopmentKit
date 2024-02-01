import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 655; // Высота окошка;
    private static final int WINDOW_WIDTH = 607; // Ширина окошка;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    JButton btnStart = new JButton("New Game"); // Создаем кнопку начала игры;
    JButton btnExit = new JButton("Exit"); // Создаем кнопку выхода из игры;
    Map map;
    SettingsWindow settings;

    GameWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("TicTacToe"); // Название окошка;
        setResizable(false);
        map = new Map();
        settings = new SettingsWindow(this);
        settings.setVisible(true);

        Map map = new Map();

        JPanel panBottom = new JPanel(new GridLayout(1, 2)); // Создаем доп. панель для кнопок;
        panBottom.add(btnStart); // Добавляем кнопку старта;
        panBottom.add(btnExit); // Добавляем кнопку выхода;
        add(panBottom, BorderLayout.SOUTH);
        add(map);


        setVisible(true); // Видимость окошка - true - видимо, false - не видимо;
    }
}
