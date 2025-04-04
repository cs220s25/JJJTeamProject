package edu.moravian.Delivery;
import static org.junit.jupiter.api.Assertions.*;

import edu.moravian.APP.GameStatus;
import org.junit.jupiter.api.Test;
public class CommandManagerTest {
    @Test
    public void testNewCommandManagerHasStatusNotStarted() {
        CommandManager commandManager = new CommandManager();
        assertEquals(GameStatus.NOT_STARTED, commandManager.status());
    }
    @Test
    public void testNewCommandManagerHasNoUsedWords() {
        CommandManager commandManager = new CommandManager();
        commandManager.start();
        commandManager.join("player1");
        commandManager.join("player2");
        assertEquals("No words used", commandManager.used());
    }
    @Test
    public void testCommandManagerInProgressHasAnagram() {
        CommandManager commandManager = new CommandManager();
        commandManager.start();
        commandManager.join("player1");
        commandManager.join("player2");
        assertFalse(commandManager.getAnagram().isEmpty());
    }
    @Test
    public void testCommandManagerExecutesStart(){
        CommandManager commandManager = new CommandManager();
        assertEquals("Game starting, type `!join` to join.", commandManager.executeCommand("start", "player1"));
    }

    @Test
    public void testCommandManagerExecutesJoin(){
        CommandManager commandManager = new CommandManager();
        commandManager.executeCommand("start", "player1");
        assertEquals("Player player2 joined the game.", commandManager.executeCommand("join", "player2"));
    }
    @Test
    public void testCommandManagerExecutesStop(){
        CommandManager commandManager = new CommandManager();
        commandManager.executeCommand("start", "player1");
        commandManager.executeCommand("join", "player2");
        assertEquals("Game stopped", commandManager.executeCommand("stop", "player1"));
    }

    @Test
    public void testCommandManagerExecutesStatus(){
        CommandManager commandManager = new CommandManager();
        commandManager.start();
        commandManager.join("player1");
        commandManager.join("player2");
        assertEquals(GameStatus.IN_PROGRESS, commandManager.status());
    }
}
