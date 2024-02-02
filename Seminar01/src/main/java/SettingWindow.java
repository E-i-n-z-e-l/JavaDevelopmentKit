import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;

public class SettingWindow extends JFrame {
    private static final int WIDTH = 300; // Устанавливаем ширину окна;
    private static final int HEIGHT = 350; // Устанавливаем высоту окна;

    private JSlider sliderSize, sliderWin; // Объявляем ползунки (регулировка размеров поля);
    private JRadioButton humanButton, robotButton; // Объявляем кнопки выбора - игра с человеком или компьютером;

    JButton btnStart; // Объявляем кнопку начала игры;

    SettingWindow(GameWindow gameWindow) {
        btnStart = new JButton("Start new game"); // Создаем кнопку начала новой игры;

        JPanel mainPanel = new JPanel(new GridLayout(3,1)); // Создаем панель на которой будем размещать игровое поле;

        setLocationRelativeTo(gameWindow); // Устанавливаем положение окна на экране относительного игрового по центру;
        setSize(WIDTH, HEIGHT); // Устанавливаем размеры окна;

        btnStart.addActionListener(new ActionListener() { // Создаем экземпляр анонимного класса ActionListener();
            @Override
            public void actionPerformed(ActionEvent e) { // В нем реализуем метод actionPerformed, который вызывается после нажатия кнопки btnStart;
                setVisible(false); // Метод setVisible(false) скрывает окно настроек;
                int mode = robotButton.isSelected() ? 1 : 0; // Проверяем выбран ли режим игры с роботом;
                gameWindow.startNewGame(mode, sliderSize.getValue(), sliderSize.getValue(), sliderWin.getValue()); // Начинаем новую игру;
            }
        });
        /*
        К панели mainPanel добавляются компоненты в следующем порядке: getModePanel(), getFieldSize() и getWinLength().
        Методы getModePanel(), getFieldSize() и getWinLength() возвращают панели с различными компонентами интерфейса,
        которые будут отображаться в окне настроек.
         */
        mainPanel.add(getModePanel());
        mainPanel.add(getFieldSize());
        mainPanel.add(getWinLength());

        // Панель mainPanel добавляется на основную панель конструктора SettingWindow с помощью метода add(mainPanel)
        add(mainPanel);
        add(btnStart, BorderLayout.SOUTH); // Размещаем кнопку старта игры внизу окна;
    }

    private JPanel getModePanel() {
        JPanel modePanel = new JPanel(new GridLayout(3, 1)); // Создаем панель(поле);

        JLabel textTitle = new JLabel("Выберите режим игры: "); // Создаем простую надпись;
        robotButton = new JRadioButton("Человек против компьютера."); // Создаем кнопки для выбора режима игры;
        humanButton = new JRadioButton("Человек против человека.");

        robotButton.setSelected(true); // Делаем кнопку robotButton выбранной по умолчанию;

        ButtonGroup group = new ButtonGroup(); // Класс ButtonGroup используется для связывания радиокнопок
                                              // таким образом, чтобы можно было выбрать только одну из них.

        group.add(robotButton); // Создаем кнопки выбора с кем играть;
        group.add(humanButton);

        modePanel.add(textTitle);  // Добавляем надпись и кнопки на панель выбора игры;
        modePanel.add(robotButton);
        modePanel.add(humanButton);

        return modePanel; // Возвращаем готовую панель;
    }

    private JPanel getFieldSize() {
        String textTitle = "Установленный размер поля: "; // Описание бегунка;

        JPanel modePanel = new JPanel(new GridLayout(3, 1)); // Создание поля;

        JLabel textTitle1 = new JLabel("Выберите размер поля"); // Описание бегунка;

        sliderSize = new JSlider(3,10,3); // Создание бегунка. Определяем начальное, конечное и текущие его положение;

        JLabel textTitle2 = new JLabel(textTitle + sliderSize.getValue()); // Создание надписи к ползунку;

        sliderSize.addChangeListener(new ChangeListener() { // Добавление слушателя изменений к ползунку;
            /**
             * Внутри анонимного класса ChangeListener переопределяется метод stateChanged,
             * который вызывается при изменении значения ползунка. Внутри метода устанавливается
             * текст у надписи textTitle2 таким образом, чтобы отображалось текущее значение ползунка.
             * <p></p>
             * Кроме того, sliderWin.setMaximum(sliderSize.getValue()) устанавливает максимальное
             * значение ползунка sliderWin равное текущему значению ползунка sliderSize.
             */
            @Override
            public void stateChanged(ChangeEvent e) {
                textTitle2.setText(textTitle + sliderSize.getValue());
                sliderWin.setMaximum(sliderSize.getValue());
            }
        });

        modePanel.add(textTitle1); // Добавление надписей и ползунка;
        modePanel.add(textTitle2);
        modePanel.add(sliderSize);

        return modePanel;
    }

    private JPanel getWinLength() {
        String textTitle = "Выберите длину для победы: "; // Создаем надпись;

        JPanel modePanel = new JPanel(new GridLayout(3, 1));

        JLabel textTitle1 = new JLabel("Установленная длина: ");

        sliderWin = new JSlider(3,10,3);

        JLabel textTitle2 = new JLabel(textTitle + sliderWin.getValue());

        sliderWin.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textTitle2.setText(textTitle + sliderWin.getValue());
            }
        });

        modePanel.add(textTitle1);
        modePanel.add(textTitle2);
        modePanel.add(sliderWin);

        return modePanel;
    }
}
