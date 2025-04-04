package edu.moravian.DataStorage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RedisManagerTest {
    @Test
    void testAnagramIsGeneratedWordOfProperSize() {
        RedisManager redisManager = new RedisManager("player1", "player2");
        assertEquals(18, redisManager.getAnagram().length());
    }
    @Test
    void testIsValidWordWorksForValidWord() {
        RedisManager redisManager = new RedisManager("player1", "player2");
        redisManager.setAnagram("hello");
        assertTrue(redisManager.isValidWord("hello"));
        assertTrue(redisManager.isValidWord("hole"));
    }
    @Test
    void testIsValidWordWorksForInvalidWord() {
        RedisManager redisManager = new RedisManager("player1", "player2");
        redisManager.setAnagram("hello");
        assertFalse(redisManager.isValidWord("helo"));
        assertFalse(redisManager.isValidWord("hollow"));
    }
    @Test
    void testIsUsedWordWorksForUsedWord() {
        RedisManager redisManager = new RedisManager("player1", "player2");
        redisManager.setAnagram("hello");
        redisManager.addPlayerWord("hello", "player1");
        assertTrue(redisManager.isUsedWord("hello"));
    }
    @Test
    void testIsUsedWordWorksForUnusedWord() {
        RedisManager redisManager = new RedisManager("player1", "player2");
        redisManager.setAnagram("strangerthisthatandthethird");
        assertTrue(redisManager.isValidWord("stranger"));
        assertFalse(redisManager.isUsedWord("stranger"));
    }
    @Test
    void testVariousWordsCanBeValidInOneAnagram() {
        RedisManager redisManager = new RedisManager("player1", "player2");
        redisManager.setAnagram("strangerthisthatandthethird");
        assertTrue(redisManager.isValidWord("stranger"));
        assertTrue(redisManager.isValidWord("that"));
        assertTrue(redisManager.isValidWord("third"));
        assertTrue(redisManager.isValidWord("strange"));
    }
    @Test
    void testGetWordsUsedReturnsEmptyStringWhenNoWordsUsed() {
        RedisManager redisManager = new RedisManager("player1", "player2");
        assertEquals("[]", redisManager.getWordsUsed());
    }
}