package edu.moravian.DataStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
public class AnagramGeneratorTest {
    @Test
    public void testAnagramGeneratorWorksWithEmptyList() {
        ArrayList<String> words = new ArrayList<>();
        Assertions.assertEquals("", AnagramGenerator.generateAnagram(words));
    }
    @Test
    public void testAnagramGeneratorCreatesAnagramOfTwoWords() {
        ArrayList<String> words = new ArrayList<>();
        words.add("hello");
        words.add("world");
        assertEquals(10, AnagramGenerator.generateAnagram(words).length());
        assertTrue(AnagramGenerator.isValidAnagram("hello", AnagramGenerator.generateAnagram(words)));
    }

    @Test
    public void testIsValidAnagramWorksForAnagramsGivenAsAnInput() {
        assertTrue(AnagramGenerator.isValidAnagram("hello", "hlelo"));
        assertFalse(AnagramGenerator.isValidAnagram("hello", "hlel"));
    }

    @Test
    public void testGenerateAnagramCreatesAnAnagramOfCorrectLength() {
        ArrayList<String> words = new ArrayList<>();
        words.add("hello");
        words.add("world");
        assertEquals(10, AnagramGenerator.generateAnagram(words).length());
    }

    @Test
    public void testIsValidAnagramReturnsFalseForAnagramsOfDifferentLength() {
        assertFalse(AnagramGenerator.isValidAnagram("hello", "he"));
    }
}
