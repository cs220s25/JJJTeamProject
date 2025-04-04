package edu.moravian.DataStorage;
import java.util.*;

public class AnagramGenerator {
    public static String generateAnagram(List<String> words) {
        ArrayList<String> anagramList = new ArrayList<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                anagramList.add(String.valueOf(c));
            }
        }
        Collections.shuffle(anagramList);
        return String.join("", anagramList);
    }
    public static boolean isValidAnagram(String word, String anagram) {
        String[] anagramChars = anagram.strip().split("");
        String[] wordChars = word.split("");
        LinkedList<String> anagramList = new LinkedList<>(Arrays.asList(anagramChars));
        for (String wordChar : wordChars) {
            if (!anagramList.removeFirstOccurrence(wordChar)) {
                return false;
            }
        }
        return true;
    }
}
