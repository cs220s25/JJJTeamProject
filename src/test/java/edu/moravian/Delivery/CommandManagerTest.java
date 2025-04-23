package edu.moravian.Delivery;
import static org.junit.jupiter.api.Assertions.*;

import edu.moravian.APP.GameController;
import edu.moravian.APP.GameStatus;
import org.junit.jupiter.api.Test;
public class CommandManagerTest {
    @Test
    public void testNewCommandManagerHasStatusNotStarted() {
        CommandManager commandManager = setCommandManager();
        assertEquals(GameStatus.NOT_STARTED, commandManager.status());
    }
    @Test
    public void testNewCommandManagerHasNoUsedWords() {
        CommandManager commandManager = setCommandManager();
        commandManager.start();
        commandManager.join("player1");
        commandManager.join("player2");
        assertEquals("No words used", commandManager.used());
    }
    @Test
    public void testCommandManagerInProgressHasAnagram() {
        CommandManager commandManager = setCommandManager();
        commandManager.start();
        commandManager.join("player1");
        commandManager.join("player2");
        assertFalse(commandManager.getAnagram().isEmpty());
    }
    @Test
    public void testCommandManagerExecutesStart(){
        CommandManager commandManager = setCommandManager();
        assertEquals("Game starting, type `!join` to join.", commandManager.executeCommand("start", "player1"));
    }

    @Test
    public void testCommandManagerExecutesJoin(){
        CommandManager commandManager = setCommandManager();
        commandManager.executeCommand("start", "player1");
        assertEquals("Player player2 joined the game.", commandManager.executeCommand("join", "player2"));
    }
    @Test
    public void testCommandManagerExecutesStop(){
        CommandManager commandManager = setCommandManager();
        commandManager.executeCommand("start", "player1");
        commandManager.executeCommand("join", "player2");
        assertEquals("Game stopped", commandManager.executeCommand("stop", "player1"));
    }

    @Test
    public void testCommandManagerExecutesStatus(){
        CommandManager commandManager = setCommandManager();
        commandManager.start();
        commandManager.join("player1");
        commandManager.join("player2");
        assertEquals(GameStatus.IN_PROGRESS, commandManager.status());
    }
    private CommandManager setCommandManager(){
        return new CommandManager(new GameController("inMemory"));
    };
}
