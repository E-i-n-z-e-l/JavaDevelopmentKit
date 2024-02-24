package org.example;
import org.apache.commons.math3.random.RandomDataGenerator;
public class Game {
    private int numberOfGames; // Количество игр что будет проведено;
    private int wins;
    private int losses;

    public Game(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public void play() {
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator(); // Создание экземпляра RandomDataGenerator
                                                                             // для генерации случайных чисел;

        for (int i = 0; i < numberOfGames; i++) {
            int chosenDoor = randomDataGenerator.nextInt(1, 3); // Случайный выбор двери игроком (число от 1 до 3);
            int winningDoor = randomDataGenerator.nextInt(1, 3); // Генерация случайного номера двери,
                                                                             // за которой находится приз (число от 1 до 3);

            // Показываем одну из не выбранных дверей, за которой нет приза:
            int revealedDoor;
            do {
                revealedDoor = randomDataGenerator.nextInt(1, 3);
            } while (revealedDoor == chosenDoor || revealedDoor == winningDoor);
            /*  Выбор двери, которую ведущий показывает, исключая дверь, выбранную игроком и дверь с призом. */

            /* Расчет новой двери игрока после показа ведущего, путем вычета из суммы всех дверей выбранной
            игроком и показанной ведущим. */
            // Меняем выбор:
            int switchedDoor = 6 - chosenDoor - revealedDoor;

            // Проверяем результат
            if (switchedDoor == winningDoor) {
                wins++;
            } else {
                losses++;
            }
        }
    }

    public void printStatistics() {
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
    }
}
