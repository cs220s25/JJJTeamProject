package edu.moravian.APP;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class GameControllerTest {
    @Test
    void testNewGameControllerHasStatusNotStarted() {
        GameController gameController = new GameController("inMemory");
        assertEquals(GameStatus.NOT_STARTED, gameController.status());
    }
    @Test
    void testNewGameControllerHasNoUsedWords() {
        GameController gameController = new GameController("inMemory");
        gameController.start();
        gameController.join("player1");
        gameController.join("player2");
        assertEquals("No words used", gameController.used());
    }

    @Test
    void testGameControllerInProgressHasAnagram() {
        GameController gameController = new GameController("inMemory");
        gameController.start();
        gameController.join("player1");
        gameController.join("player2");
        assertFalse(gameController.getAnagram().isEmpty());
    }

    @Test
    void testGameControllerInProgressHasStatusInProgress() {
        GameController gameController = new GameController("inMemory");
        gameController.start();
        gameController.join("player1");
        gameController.join("player2");
        assertEquals(GameStatus.IN_PROGRESS, gameController.status());
    }

    @Test
    void testGameControllerInProgressHasStatusNotStarted() {
        GameController gameController = new GameController("inMemory");
        gameController.start();
        gameController.join("player1");
        gameController.join("player2");
        gameController.stop();
        assertEquals(GameStatus.NOT_STARTED, gameController.status());
    }

    @Test
    void testGameControllerInProgressHasStatusNotStartedAfterStop() {
        GameController gameController = new GameController("inMemory");
        gameController.start();
        gameController.join("player1");
        gameController.join("player2");
        gameController.stop();
        assertEquals(GameStatus.NOT_STARTED, gameController.status());
    }
    @Test
    void testGameControllerInProgressHasStatusNotStartedAfterStopAndStart() {
        GameController gameController = new GameController("inMemory");
        gameController.start();
        gameController.join("player1");
        gameController.join("player2");
        gameController.stop();
        gameController.start();
        assertEquals(GameStatus.STARTING, gameController.status());
    }
}