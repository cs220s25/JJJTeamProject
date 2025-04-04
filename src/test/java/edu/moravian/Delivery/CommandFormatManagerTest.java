package edu.moravian.Delivery;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class CommandFormatManagerTest {
    @Test
    void testPrefixChecker() {
        assertTrue(CommandFormatter.isPrefix("hello", "he"));
        assertFalse(CommandFormatter.isPrefix("hello", "hi"));
    }

    @Test
    void testPrefixCheckerEmpty() {
        assertTrue(CommandFormatter.isPrefix("", ""));
    }

    @Test
    void testPrefixCheckerEmptyPrefix() {
        assertTrue(CommandFormatter.isPrefix("hello", ""));
    }

    @Test
    void testPrefixCheckerEmptyWord() {
        assertFalse(CommandFormatter.isPrefix("", "he"));
    }

    @Test
    void testPrefixCheckerWordsWithCommandPrefix() {
        assertTrue(CommandFormatter.isPrefix("!hello", "!"));
        assertFalse(CommandFormatter.isPrefix("hello", "!"));
    }

    @Test
    void testGetCommandSignature() {
        assertEquals("hello", CommandFormatter.getCommandSignature("!hello"));
        assertEquals("", CommandFormatter.getCommandSignature("hello"));
    }

    @Test
    void testGetCommandSignatureEmpty() {
        assertEquals("", CommandFormatter.getCommandSignature(""));
    }

    @Test
    void testGetCommandSignatureEmptyCommand() {
        assertEquals("", CommandFormatter.getCommandSignature("!"));
        assertEquals(" ", CommandFormatter.getCommandSignature("! "));
    }

}