import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;

public class Map extends JPanel {

    private static final Random RANDOM = new Random(); // Создается статическая константная переменная RANDOM
                                                      // класса Random. Она используется для генерации случайных
                                                      // чисел в игре;
    private static final int HUMAN_DOT = 1; // Создается статическая константа HUMAN_DOT, которая представляет
                                            // значение для игрового поля, соответствующее ходу игрока;
    private static final int AI_DOT = 2; // Создается статическая константа AI_DOT, которая представляет
                                        // значение для игрового поля, соответствующее ходу компьютера;
    private static final int EMPTY_DOT = 0; // Создается статическая константа EMPTY_DOT, которая представляет
                                            // значение для игрового поля, соответствующее пустой клетке;
    private static final int PADDING = 10; // Создается статическая константа PADDING, которая определяет
                                          // отступы вокруг клеток игрового поля для визуального оформления

    private int gameStateType; // Целочисленная переменная gameStateType, которая хранит текущее состояние игры.
                               // Значение STATE_GAME (0) означает, что игра продолжается,
                               // STATE_WIN_HUMAN (1) - что игрок победил,
                               // STATE_WIN_AI (2) - что компьютер победил,
                               // STATE_DRAW (3) - что игра закончилась в ничью
    private static final int STATE_GAME = 0;
    private static final int STATE_WIN_HUMAN = 1;
    private static final int STATE_WIN_AI = 2;
    private static final int STATE_DRAW = 3;

    private static final String MSG_WIN_HUMAN = "Победил игрок!"; // Создается статическая константа MSG_WIN_HUMAN,
                                                                  // содержащая сообщение о победе игрока;
    private static final String MSG_WIN_AI = "Победил компьютер!"; // Создается статическая константа MSG_WIN_AI,
                                                                   // содержащая сообщение о победе компьютера;
    private static final String MSG_DRAW = "Ничья!"; // Создается статическая константа MSG_DRAW,
                                                     // содержащая сообщение о ничьей;

    private int width, height, cellWidth, cellHeight; // Целочисленные переменные, определяющие размеры
                                                      // игрового поля и размеры клеток;
    private int mode, fieldSizeX, fieldSizeY, winLen; // Целочисленные переменные, которые определяют режим игры,
                                                      // размеры игрового поля по горизонтали и вертикали, а также
                                                      // длину выигрышной последовательности;
    private int[][] field; // Двумерный массив целых чисел, представляющий игровое поле.
                           // Каждая ячейка массива содержит значение EMPTY_DOT, HUMAN_DOT или AI_DOT,
                           // в зависимости от состояния ячейки;
    private boolean gameWork; // Логическая переменная gameWork, которая указывает,
                              // идет ли игра в данный момент или остановлена;
    /**
    Конструктор класса Map.
     */
    Map() {
        setBackground(Color.WHITE); // Устанавливает фон панели игрового поля в белый цвет;
        /*
        Добавляет слушатель мыши к панели игрового поля. Внутри анонимного класса MouseAdapter
        переопределяется метод mouseReleased, который вызывается при отпускании кнопки мыши.
        Если игра идет (gameWork равно true), вызывается метод update(e), передавая событие MouseEvent e.
        Это означает, что при отпускании кнопки мыши будет происходить обновление игрового поля;
         */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (gameWork) {
                    update(e);
                }
            }
        });
    }

    /**
     * Приватный метод, который инициализирует игровое поле. Вот что происходит внутри него:<p></p>
     *     - field = new int[fieldSizeY][fieldSizeX]; - создает новый двумерный массив field, размером fieldSizeY
     *     на fieldSizeX. Это инициализирует игровое поле, где каждая ячейка массива будет содержать значение
     *     EMPTY_DOT (0), что означает пустую клетку.
     */
    private void initMap() {
        field = new int[fieldSizeY][fieldSizeX];
    }

    void startNewGame(int mode, int sizeX, int sizeY, int winLen) {
        this.mode = mode;
        this.fieldSizeX = sizeX;
        this.fieldSizeY = sizeY;
        this.winLen = winLen;

        initMap(); // Инициализируем игровое поле с помощью метода initMap();
        gameWork = true; // Устанавливает значение true переменной gameWork, что означает, что игра активна и продолжается;
        gameStateType = STATE_GAME; // устанавливает значение STATE_GAME, указывающее, что игра находится
                                    // в режиме игры (не закончена, не ничья и не победа игрока/компьютера);

        repaint(); // Инициирует перерисовку игрового поля, чтобы обновить его в соответствии с новыми параметрами;
    }

    private void update(MouseEvent mouseEvent) {
        int x = mouseEvent.getX() / cellWidth; // вычисляет номер столбца x на игровом поле, куда был сделан клик мышью,
                                            // путем деления координаты x события mouseEvent на ширину ячейки cellWidth;
        int y = mouseEvent.getY() / cellHeight;
        /*
        Проверяет, является ли выбранная ячейка (x, y) допустимой и пустой. Если условие не выполняется
        (ячейка недопустима или не пуста), то метод просто возвращает управление и ничего не делает
         */
        if (!isValidCell(x, y) || !isEmptyCell(x, y)) {
            return;
        }
        field[y][x] = HUMAN_DOT; // Устанавливает значение HUMAN_DOT в выбранную ячейку (x, y) игрового поля,
                                 // что соответствует ходу игрока;
        // Вызывает метод checkEndGame для проверки окончания игры после хода игрока.
        //- Если метод checkEndGame возвращает true, значит игра завершена победой игрока
        // и метод просто возвращает управление без выполнения оставшейся части кода;
        if (checkEndGame(HUMAN_DOT, STATE_WIN_HUMAN)) {
            return;
        }
        aiTurn(); // Вызывает метод aiTurn(), который отвечает за ход компьютера;
        repaint(); // Инициирует перерисовку игрового поля для обновления его состояния после хода игрока;
        checkEndGame(AI_DOT, STATE_WIN_AI); // Вызывает метод checkEndGame для проверки окончания игры после
                                            // хода компьютера, чтобы обработать возможную победу компьютера;
    }

    /**
     * Метод для тестирования, выводит на консоль текущее состояние игрового поля в виде матрицы.
     * Проходится циклом по каждому ряду и выводит его.
     */
    private void testBoard(){
        for (int i = 0; i < 3; i++) {
            System.out.println(Arrays.toString(field[i]));
        }
        System.out.println();
    }

    /**
     * Проверяет, является ли указанная ячейка (x, y) допустимой на игровом поле.
     * Возвращает true, если ячейка находится в пределах игрового поля, false - в противном случае
     * @param x
     * @param y
     * @return
     */
    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    /**
     * Проверяет, является ли указанная ячейка (x, y) пустой. Возвращает true, если ячейка пуста
     * (содержит значение EMPTY_DOT), false - в противном случае.
     * @param x
     * @param y
     * @return
     */
    private boolean isEmptyCell(int x, int y) {
        return field[y][x] == EMPTY_DOT;
    }

    /**
     * Метод, который отвечает за ход компьютера. Генерирует случайные координаты ячейки (x, y)
     * и ставит в нее значение AI_DOT, если ячейка пустая.
     */
    private void aiTurn() {
        int x, y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y));
        field[y][x] = AI_DOT;
    }

    /**
     * Проверяет, заполнено ли игровое поле полностью. Проходит двумерный массив field и
     * если находит пустую ячейку (EMPTY_DOT), возвращает false. Если все ячейки заняты,
     * возвращает true, что означает, что поле полностью заполнено.
     * @return
     */
    private boolean isMapFull() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Метод для проверки окончания игры. Принимает значение игровой фишки dot и тип окончания игры
     * gameOverType. Сначала метод вызывает checkWin(dot) и если возвращает true, то устанавливает
     * gameStateType в gameOverType, перерисовывает игровое поле и возвращает true.
     * <p></p>
     * Затем, если игровое поле полностью заполнено, устанавливает gameStateType в STATE_DRAW, перерисовывает
     * игровое поле и возвращает true. В противном случае, возвращает false, что означает, что игра продолжается.
     * @param dot
     * @param gameOverType
     * @return
     */
    private boolean checkEndGame(int dot, int gameOverType) {
        if (checkWin(dot)) {
            this.gameStateType = gameOverType;
            repaint();
            return true;
        } else if (isMapFull()) {
            this.gameStateType = STATE_DRAW;
            repaint();
            return true;
        }
        return false;
    }

    /**
     * Метод для проверки наличия выигрышной последовательности длиной winLen для заданной игровой фишки dot.
     * Метод проходит по каждой ячейке игрового поля и вызывает метод checkLine для проверки наличия выигрышной
     * последовательности в горизонтальном, вертикальном или диагональном направлениях. Если хотя бы одно из
     * выполнений checkLine вернет true, то метод возвращает true, что означает, что игрок с фишкой dot выиграл игру.
     * @param dot
     * @return
     */
    private boolean checkWin(int dot){
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (checkLine(i, j, 1, 0, winLen, dot)) return true;
                if (checkLine(i, j, 1, 1, winLen, dot)) return true;
                if (checkLine(i, j, 0, 1, winLen, dot)) return true;
                if (checkLine(i, j, 1, -1, winLen, dot)) return true;
            }
        }
        return false;
    }

    /**
     * Метод для проверки наличия выигрышной последовательности длиной len в заданном направлении (vx, vy)
     * от ячейки (x, y). Метод выполняет цикл от начальной ячейки до конечной ячейки и проверяет, содержат
     * ли все ячейки на пути фишки dot. Если все ячейки содержат dot, то метод возвращает true, что означает,
     * что выигрышная последовательность найдена.
     * @param x
     * @param y
     * @param vx
     * @param vy
     * @param len
     * @param dot
     * @return
     */
    private boolean checkLine(int x, int y, int vx, int vy, int len, int dot){
        int far_x = x + (len - 1) * vx;
        int far_y = y + (len - 1) * vy;
        if (!isValidCell(far_x, far_y)){
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (field[y + i * vy][x + i * vx] != dot){
                return false;
            }
        }
        return true;
    }

    /**
     * Метод, переопределенный из класса JPanel. Он отвечает за отрисовку игрового поля.
     * Если gameWork равен true, метод вызывает метод render, который выполняет отрисовку игрового поля.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameWork) {
            render(g);
        }
    }

    /**
     * Метод отвечает за отрисовку игрового поля и игровых фишек. Вот что происходит внутри него:
     *    - Вычисляются текущие размеры панели игры (width, height) и размер каждой ячейки (cellWidth, cellHeight)
     *    на основе размеров окна и количества ячеек. <p></p>
     *    - Отрисовываются горизонтальные и вертикальные линии, которые образуют клетки игрового поля. <p></p>
     *    - Проходятся все ячейки игрового поля (field). Если ячейка пустая (EMPTY_DOT), то пропускаем ее.
     *    Если ячейка содержит фишку игрока (HUMAN_DOT), то рисуем крестик внутри ячейки.
     *    Если ячейка содержит фишку компьютера (AI_DOT), то рисуем круг внутри ячейки. <p></p>
     *    - Если gameStateType не равно STATE_GAME (т.е. игра окончена), вызывается метод showMessage,
     *    отвечающий за отображение сообщения о результате игры.
     * @param g
     */
    private void render(Graphics g) {
        width = getWidth();
        height = getHeight();
        cellWidth = width / fieldSizeX;
        cellHeight = height / fieldSizeY;

        g.setColor(Color.BLACK);
        for (int h = 0; h < fieldSizeX; h++) {
            int y = h * cellHeight;
            g.drawLine(0, y, width, y);
        }
        for (int w = 0; w < fieldSizeX; w++) {
            int x = w * cellWidth;
            g.drawLine(x, 0, x, height);
        }

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == EMPTY_DOT){
                    continue;
                }
                if (field[y][x] == HUMAN_DOT) {
                    g.drawLine(x * cellWidth + PADDING, y * cellHeight + PADDING,
                            (x + 1) * cellWidth - PADDING, (y + 1) * cellHeight - PADDING);
                    g.drawLine(x * cellWidth + PADDING, (y + 1) * cellHeight - PADDING,
                            (x + 1) * cellWidth - PADDING, y * cellHeight + PADDING);
                } else if (field[y][x] == AI_DOT) {
                    g.drawOval(x * cellWidth + PADDING, y * cellHeight + PADDING,
                            cellWidth - PADDING * 2, cellHeight - PADDING * 2);
                } else {
                    throw new RuntimeException("unchecked value " + field[y][x] +
                            " in cell: x=" + x + " y=" + y);
                }
            }
        }
        if (gameStateType != STATE_GAME){
            showMessage(g);
        }
    }

    /**
     * Метод для отображения сообщения о результате игры. Вот что он делает: <p></p>
     *    - Заполняет нижнюю часть окна игры прямоугольником темного цвета. <p></p>
     *    - Устанавливает цвет текста и шрифт. <p></p>
     *    - Используя оператор switch-case, в зависимости от значения gameStateType,
     *    отображает соответствующее сообщение (MSG_DRAW, MSG_WIN_HUMAN, MSG_WIN_AI). <p></p>
     *    - Устанавливает gameWork в false, что останавливает возможность продолжения игры.
     * @param g
     */
    private void showMessage(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, getHeight() / 2, getWidth(), 70);
        g.setColor(Color.GREEN);
        g.setFont(new Font("Times new roman", Font.BOLD, 48));
        switch (gameStateType){
            case STATE_DRAW -> g.drawString(MSG_DRAW, 180, getHeight() / 2 + 60);
            case STATE_WIN_HUMAN -> g.drawString(MSG_WIN_HUMAN, 20, getHeight() / 2 + 60);
            case STATE_WIN_AI -> g.drawString(MSG_WIN_AI, 70, getHeight() / 2 + 60);
            default -> throw new RuntimeException("Unchecked gameOverState: " + gameStateType);
        }
        gameWork = false;
    }
}
