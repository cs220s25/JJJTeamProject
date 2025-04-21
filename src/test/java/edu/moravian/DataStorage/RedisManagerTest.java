package edu.moravian.DataStorage;

import com.github.fppt.jedismock.RedisServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RedisManagerTest {
    static RedisServer redisServer;

    static {
        try {
            redisServer = RedisServer.newRedisServer().start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testAnagramIsGeneratedWordOfProperSize() {
        RedisManager redisManager = new RedisManager("player1", "player2", redisServer);
        assertEquals(18, redisManager.getAnagram().length());
    }
    @Test
    void testIsValidWordWorksForValidWord() {
        RedisManager redisManager = new RedisManager("player1", "player2", redisServer);
        redisManager.setAnagram("hello");
        assertTrue(redisManager.isValidWord("hello"));
        assertTrue(redisManager.isValidWord("hole"));
    }
    @Test
    void testIsValidWordWorksForInvalidWord() {
        RedisManager redisManager = new RedisManager("player1", "player2", redisServer);
        redisManager.setAnagram("hello");
        assertFalse(redisManager.isValidWord("helo"));
        assertFalse(redisManager.isValidWord("hollow"));
    }
    @Test
    void testIsUsedWordWorksForUsedWord() {
        RedisManager redisManager = new RedisManager("player1", "player2", redisServer);
        redisManager.setAnagram("hello");
        redisManager.addPlayerWord("hello", "player1");
        assertTrue(redisManager.isUsedWord("hello"));
    }
    @Test
    void testIsUsedWordWorksForUnusedWord() {
        RedisManager redisManager = new RedisManager("player1", "player2", redisServer);
        redisManager.setAnagram("strangerthisthatandthethird");
        assertTrue(redisManager.isValidWord("stranger"));
        assertFalse(redisManager.isUsedWord("stranger"));
    }
    @Test
    void testVariousWordsCanBeValidInOneAnagram() {
        RedisManager redisManager = new RedisManager("player1", "player2", redisServer);
        redisManager.setAnagram("strangerthisthatandthethird");
        assertTrue(redisManager.isValidWord("stranger"));
        assertTrue(redisManager.isValidWord("that"));
        assertTrue(redisManager.isValidWord("third"));
        assertTrue(redisManager.isValidWord("strange"));
    }
    @Test
    void testGetWordsUsedReturnsEmptyStringWhenNoWordsUsed() {
        RedisManager redisManager = new RedisManager("player1", "player2", redisServer);
        assertEquals("[]", redisManager.getWordsUsed());
    }

    @Test
    void testDeleteOldGameKeys(){
        RedisManager redisManager = new RedisManager("player1", "player2", redisServer);
        redisManager.setAnagram("hello");
        redisManager.addPlayerWord("hello", "player1");
        redisManager.deleteOldGameKeys();
        assertEquals(0, redisManager.getPlayerScores("player1").size());
    }
    @AfterAll
    static void tearDown() throws IOException {
        redisServer.stop();
    }
}