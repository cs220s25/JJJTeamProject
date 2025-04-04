package edu.moravian.DataStorage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class InMemoryManagerTest {
    @Test
    void testAnagramIsGeneratedWordOfProperSize() {
        InMemoryManager manager = new InMemoryManager("player1", "player2");
        assertEquals(18, manager.getAnagram().length());
    }
    @Test
    void testIsValidWordWorksForValidWord() {
        InMemoryManager manager = new InMemoryManager("player1", "player2");
        manager.setAnagram("hello");
        assertTrue(manager.isValidWord("hello"));
        assertTrue(manager.isValidWord("hole"));
    }
    @Test
    void testIsValidWordWorksForInvalidWord() {
        InMemoryManager manager = new InMemoryManager("player1", "player2");
        manager.setAnagram("hello");
        assertFalse(manager.isValidWord("helo"));
        assertFalse(manager.isValidWord("hollow"));
    }
    @Test
    void testIsUsedWordWorksForUsedWord() {
        InMemoryManager manager = new InMemoryManager("player1", "player2");
        manager.setAnagram("hello");
        manager.addPlayerWord("hello", "player1");
        assertTrue(manager.isUsedWord("hello"));
    }
    @Test
    void testIsUsedWordWorksForUnusedWord() {
        InMemoryManager manager = new InMemoryManager("player1", "player2");
        manager.setAnagram("strangerthisthatandthethird");
        assertTrue(manager.isValidWord("stranger"));
        assertFalse(manager.isUsedWord("stranger"));
    }
    @Test
    void testVariousWordsCanBeValidInOneAnagram() {
        InMemoryManager manager = new InMemoryManager("player1", "player2");
        manager.setAnagram("strangerthisthatandthethird");
        assertTrue(manager.isValidWord("stranger"));
        assertTrue(manager.isValidWord("that"));
        assertTrue(manager.isValidWord("third"));
        assertTrue(manager.isValidWord("strange"));
    }

    @Test
    void testPlayerScoresAreCorrect() {
        InMemoryManager manager = new InMemoryManager("player1", "player2");
        manager.setAnagram("hello");
        manager.addPlayerWord("hello", "player1");
        manager.addPlayerWord("hole", "player1");
        manager.addPlayerWord("hollow", "player1");
        manager.addPlayerWord("hollow", "player2");
        assertEquals(3, manager.getPlayerScores("player1").size());
        assertEquals(1, manager.getPlayerScores("player2").size());
    }
}