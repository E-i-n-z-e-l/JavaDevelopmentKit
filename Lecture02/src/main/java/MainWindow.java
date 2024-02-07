import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public static final int POS_X = 400;
    public static final int POS_Y = 200;
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    private final Sprite[] sprites = new Sprite[20]; // Определяем количество шариков;

    private MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles");
        /* В цикле for в конструкторе класса создаются объекты Ball и сохраняются в массив sprites.
        Для каждого элемента массива вызывается конструктор класса Ball, создавая новый шарик и
        сохраняя его в массив. */
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Ball();
        }
        /* Затем создается экземпляр класса MainCanvas с передачей текущего экземпляра MainWindow
        в качестве параметра. Объект MainCanvas добавляется на окно с помощью метода add(). */
        MainCanvas canvas = new MainCanvas(this);
        add(canvas);
        setVisible(true);
    }

    /**
     * Метод onDrawFrame() вызывается каждый кадр для отрисовки и обновления состояния элементов на экране.
     * Он вызывает методы update() и render().
     * @param canvas
     * @param g
     * @param deltaTime
     */
    public void onDrawFrame(MainCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    /**
     * Метод update() принимает экземпляр MainCanvas и проходится по всем элементам
     * массива sprites, вызывая их методы update().
     * @param canvas
     * @param deltaTime
     */
    private void update(MainCanvas canvas, float deltaTime) {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].update(canvas, deltaTime);

        }
    }

    /**
     * Метод render() также принимает экземпляр MainCanvas и объект Graphics и проходится по всем
     * элементам массива sprites, вызывая их методы render().
     * @param canvas
     * @param g
     */
    private void render(MainCanvas canvas, Graphics g) {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].render(canvas, g);
        }
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
