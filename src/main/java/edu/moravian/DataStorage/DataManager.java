package edu.moravian.DataStorage;

import java.util.ArrayList;

public interface DataManager {
    boolean isValidWord(String word);
    boolean isUsedWord(String word);
    void addPlayerWord(String word, String playerName);
    String getAnagram();
    void newAnagram();
    ArrayList<Score> getPlayerScores(String playerName);
    boolean isValidAnagram(String word);
    String getWordsUsed();
}
