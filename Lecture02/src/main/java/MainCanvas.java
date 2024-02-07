import javax.swing.*;
import java.awt.*;

public class MainCanvas extends JPanel {
    private final MainWindow controller;
    private long lastFrameTime;

    MainCanvas(MainWindow controller) {
        this.controller = controller;
        lastFrameTime=System.nanoTime(); // Возвращает текущее время в наносекундах;
    }

    /**
     * Метод paintComponent(Graphics g) переопределен из класса JPanel и используется для отрисовки графики на панели.
     * <p></p>
     * Внутри метода сначала вызывается метод paintComponents(g) родительского класса, чтобы отрисовать компоненты,
     * которые добавлены на панель. Затем следует задержка в 16 миллисекунд (или около того), чтобы ограничить частоту
     * отрисовки кадров. После этого вычисляется deltaTime - время, прошедшее с момента последней отрисовки в секундах
     * (используется разница между текущим временем и сохраненным в lastFrameTime).
     * Затем вызывается метод onDrawFrame() у контроллера (MainWindow) с передачей текущего экземпляра MainCanvas,
     * объекта Graphics и deltaTime в качестве аргументов. Последним шагом заносится текущее время в lastFrameTime
     * и вызывается метод repaint() для перерисовки панели.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight()); // Удаляем след от шариков;
        super.paintComponents(g);
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        float deltaTime=(System.nanoTime()-lastFrameTime)*0.000000001f;
        controller.onDrawFrame(this,g,deltaTime);
        lastFrameTime=System.nanoTime();
        repaint();
    }

    /* Методы getLeft(), getRight(), getTop() и getBottom() используются для получения границ панели.
    getLeft() возвращает 0 (левая граница), getRight() возвращает ширину панели минус 1 (правая граница),
    getTop() возвращает 0 (верхняя граница) и getBottom() возвращает высоту панели минус 1 (нижняя граница). */
    public int getLeft() {
        return 0;
    }

    public int getRight() {
        return getWidth() - 1;
    }

    public int getTop() {
        return 0;
    }

    public int getBottom() {
        return getHeight() - 1;
    }
}
