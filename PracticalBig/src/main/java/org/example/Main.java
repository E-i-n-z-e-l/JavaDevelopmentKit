package org.example;

public class Main {
    public static void main(String[] args) {
        int numberOfGames = 1000;

        Game game = new Game(numberOfGames);
        game.play();
        game.printStatistics();
    }
}
