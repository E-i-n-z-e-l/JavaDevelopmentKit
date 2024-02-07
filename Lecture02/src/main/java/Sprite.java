import java.awt.*;

/**
 * Этот класс называется "Sprite" и является абстрактным, что означает, что он не может быть
 * использован для создания объектов напрямую, только для наследования другими классами.
 */
public abstract class Sprite {
    /* У него есть несколько полей (x, y, halfWidth, halfHeight), которые используются для
    определения позиции и размеров спрайта.*/
    protected float x;
    protected float y;
    protected float halfWidth;
    protected float halfHeight;

    /* - getLeft() и setLeft() используются для получения и установки левой границы спрайта.
    Левая граница вычисляется как x - половина ширины. */
    protected float getLeft() {
        return x - halfWidth;
    }

    protected void setLeft(float left) {
        x = left + halfWidth;
    }

    /* getRight() и setRight() используются для получения и установки правой границы спрайта.
    Правая граница вычисляется как x + половина ширины. */
    protected float getRight() {
        return x + halfWidth;
    }

    protected void setRight(float right) {
        x = right - halfWidth;
    }

    /* getTop() и setTop() используются для получения и установки верхней границы спрайта.
    Верхняя граница вычисляется как y - половина высоты. */
    protected float getTop() {
        return y - halfHeight;
    }

    protected void setTop(float top) {
        y = top + halfHeight;
    }

    /* getBottom() и setBottom() используются для получения и установки нижней границы спрайта.
    Нижняя граница вычисляется как y + половина высоты. */
    protected float getBottom() {
        return y + halfHeight;
    }

    protected void setBottom(float bottom) {
        y = bottom - halfHeight;
    }

    /* getWidth() используется для получения ширины спрайта. Ширина вычисляется как два раза половина ширины. */
    protected float getWidth() {
        return 2f * halfWidth;
    }

    /* getHeight() используется для получения высоты спрайта. Высота вычисляется как два раза половина высоты. */
    protected float getHeight() {
        return 2f * halfHeight;
    }

    /**
     * update(MainCanvas canvas, float deltaTime) используется для обновления состояния спрайта на каждом шаге игры.
     * Он принимает экземпляр класса MainCanvas и время, прошедшее с момента последнего обновления.
     * @param canvas
     * @param deltaTime
     */
    void update(MainCanvas canvas, float deltaTime) {
    }

    /**
     * render(MainCanvas canvas, Graphics g) используется для отрисовки спрайта на экране.
     * Он также принимает экземпляр класса MainCanvas и объект Graphics, который используется
     * для отображения графики.
     * @param canvas
     * @param g
     */
    void render(MainCanvas canvas, Graphics g) {
    }
}
