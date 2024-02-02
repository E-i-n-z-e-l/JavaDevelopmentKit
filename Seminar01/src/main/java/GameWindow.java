import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private static final int WIDTH = 555; // Устанавливаем ширину окна;
    private static final int HEIGHT = 507; // Устанавливаем высоту окна;

    JButton btnStart, btnExit; // Создаем кнопки;
    SettingWindow settingWindow; // Это переменная типа SettingWindow, которая представляет окно настроек игры;
    Map map; // Это переменная типа Map, которая представляет игровое поле;

    GameWindow(){ // Создаем конструктор игрового поля;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // устанавливает операцию закрытия окна по умолчанию,
                                                       // чтобы при закрытии окна игры, программа завершалась
        setSize(WIDTH, HEIGHT); // устанавливает размер окна, используя значения константных переменных WIDTH и HEIGHT;
        setLocationRelativeTo(null); //

        setTitle("TicTacToe");// Устанавливаем заголовок окна игры;
        setResizable(false); // Отключает возможность изменения размеров окна пользователем.
                            // Окно будет иметь фиксированный размер;
        btnStart = new JButton("New Game"); // Создаем кнопки;
        btnExit = new JButton("Exit");
        /*
        Создает новый объект SettingWindow и передает текущий объект GameWindow в качестве аргумента конструктора
        SettingWindow. Таким образом, объект GameWindow становится доступным для вызова методов и доступа к свойствам
        объекта SettingWindow
         */
        settingWindow = new SettingWindow(this);
        map = new Map(); // создает новый объект Map, который представляет игровое поле;
        /*
        Устанавливается слушатель действия ActionListener для кнопки btnExit.
        При нажатии на кнопку будет выполнено действие, определенное внутри метода actionPerformed.
        В данном случае, при нажатии кнопки программа будет завершаться с помощью System.exit(0).
         */
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        /*
        Устанавливается слушатель действия ActionListener для кнопки btnStart.
        При нажатии на кнопку будет выполнено действие, определенное внутри метода actionPerformed.
        В данном случае, при нажатии кнопки будет устанавливаться видимость окна настроек игры
        settingWindow (settingWindow.setVisible(true)).
         */
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingWindow.setVisible(true);
            }
        });
        /*
        Создается панель panBottom с использованием компонента JPanel и устанавливается для нее
        менеджер компоновки GridLayout с одной строкой и двумя столбцами.
         */
        JPanel panBottom = new JPanel(new GridLayout(1, 2));

        panBottom.add(btnStart); // Добавляем кнопки;
        panBottom.add(btnExit);
        /*
        Add(panBottom, BorderLayout.SOUTH); добавляет панель panBottom на основную панель игры с
        использованием расположения BorderLayout. Это поместит панель с кнопками в нижнюю часть окна.
         */
        add(panBottom, BorderLayout.SOUTH);
        add(map); // добавляет игровое поле map на основную панель игры;

        setVisible(true); // делает окно игры видимым на экране;
    }

    /**
     * Метод принимает четыре аргумента: mode, sizeX, sizeY, winLen. Эти аргументы указывают на: <p></p>
     * Режим игры (mode); <p></p>
     * Размеры игрового поля по горизонтали (sizeX) и вертикали (sizeY); <p></p>
     * А также длину выигрышной последовательности (winLen).<p></p>
     * @param mode
     * @param sizeX
     * @param sizeY
     * @param winLen
     */
    void startNewGame(int mode, int sizeX, int sizeY, int winLen){
        map.startNewGame(mode, sizeX, sizeY, winLen);
    }
}
