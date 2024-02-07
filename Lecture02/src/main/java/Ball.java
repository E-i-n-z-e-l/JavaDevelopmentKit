import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Ball extends Sprite {
    private static Random random = new Random(); // это статическое поле типа Random, которое
                                                 // будет использоваться для генерации случайного числа;
    private final Color color; // это конечное поле типа Color, которое представляет цвет шарика;
    /* private float vX; и private float vY; - это поля, которые представляют скорость движения
    шарика по осям X и Y соответственно. */
    private float vX;
    private float vY;

    Ball() {
        /* Внутри конструктора устанавливаются значения для полей halfHeight, halfWidth, color, vX и vY.
        Поле halfHeight получает случайное значение между 20 и 50, все остальные поля получают случайные
        значения в диапазоне от 100 до 300. */
        halfHeight = 40 + (float) (Math.random() * 50f);
        halfWidth = 60 + (float) (Math.random() * 70f);
        color = new Color(random.nextInt());
        vX = 100f + (float) (Math.random() * 200f);
        vY = 100f + (float) (Math.random() * 200f);

    }

    /**
     * Метод render(MainCanvas canvas, Graphics g) переопределен из класса Sprite. Он используется
     * для отображения шарика на экране. Внутри метода устанавливается цвет графического объекта g
     * с помощью метода setColor() и заполняется овал с помощью метода fillOval().
     * <p></p>
     * Координаты и размеры овала получаются с помощью методов getLeft(), getTop(), getWidth() и
     * getHeight() из класса Sprite.
     * @param canvas
     * @param g
     */
    @Override
    void render(MainCanvas canvas, Graphics g) {
        g.setColor(color);
        g.fillOval((int) getLeft(), (int) getTop(), (int) getWidth(), (int) getHeight());
    }

    /**
     * Метод update(MainCanvas canvas, float deltaTime) также переопределен из класса Sprite.
     * Он используется для обновления состояния шарика на каждом шаге игры. Внутри метода
     * обновляются позиция шарика на основе его текущей скорости vX и vY и прошедшего времени deltaTime.
     * Затем проверяются границы canvas и, если шарик выходит за пределы границ, его позиция и направление
     * изменяются, чтобы он отскакивал от стен.
     * @param canvas
     * @param deltaTime
     */
    @Override
    void update(MainCanvas canvas, float deltaTime) {
        x += vX * deltaTime;
        y += vY * deltaTime;
        if (getLeft() < canvas.getLeft()) {
            setLeft(canvas.getLeft());
            vX = -vX;
        }
        if (getRight() > canvas.getRight()) {
            setRight(canvas.getRight());
            vX = -vX;
        }
        if (getTop() < canvas.getTop()) {
            setTop(canvas.getTop());
            vY = -vY;
        }
        if (getBottom() > canvas.getBottom()) {
            setBottom(canvas.getBottom());
            vY = -vY;
        }
    }
}
