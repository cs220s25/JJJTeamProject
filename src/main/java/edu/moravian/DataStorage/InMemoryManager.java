package edu.moravian.DataStorage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryManager implements DataManager{
    private final HashMap<Integer, ArrayList<String>> lexicon;
    private final ArrayList<String> players;
    private final ArrayList<String> usedWords;
    private final HashMap<String, ArrayList<String>> playerWords;
    private String anagram;
    private final HashMap<Integer, Integer> scores;

    public InMemoryManager(String player1, String player2) {
        lexicon = new HashMap<>();
        players = new ArrayList<>();
        usedWords = new ArrayList<>();
        playerWords = new HashMap<>();
        scores = new HashMap<>();
        initializeLexiconData();
        initializePlayerTags(player1, player2);
        initializeScores();
        newAnagram();
    }
    private void initializeLexiconData(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("Files/large.txt"));
            String line = reader.readLine();
            while (line != null) {
                int wordLength = line.length();
                if (lexicon.containsKey(wordLength)) {
                    lexicon.get(wordLength).add(line);
                } else {
                    lexicon.put(wordLength, new ArrayList<>());
                    lexicon.get(wordLength).add(line);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Cannot read file");
        }
    }
    private void initializePlayerTags(String player1, String player2) {
        players.add(player1);
        players.add(player2);
        playerWords.put(player1, new ArrayList<>());
        playerWords.put(player2, new ArrayList<>());
    }
    private void initializeScores() {
        for (int i = 3; i < 19; i++) {
            scores.put(i, i + 3);
        }
    }
    @Override
    public boolean isValidWord(String word) {
        return lexicon.get(word.length()).contains(word) && isValidAnagram(word);

    }
    @Override
    public boolean isUsedWord(String word) {
        return usedWords.contains(word);
    }

    @Override
    public String getWordsUsed() {
        return usedWords.toString();
    }

    @Override
    public void addPlayerWord(String word, String playerName) {
        playerWords.get(playerName).add(word);
        usedWords.add(word);
    }

    @Override
    public String getAnagram() {
        return anagram;
    }

    public void setAnagram(String anagram) {
        this.anagram = anagram;
    }

    @Override
    public void newAnagram() {
        ArrayList<String> words = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            words.add(lexicon.get(6).get((int) (Math.random() * lexicon.get(6).size())));
        }
        anagram = AnagramGenerator.generateAnagram(words);
    }

    @Override
    public ArrayList<Score> getPlayerScores(String playerName) {
        ArrayList<String> words = playerWords.get(playerName);
        ArrayList<Score> scores = new ArrayList<>();
        for (String word : words) {
            int value = this.scores.get(word.length());
            scores.add(new Score(word, value));
        }
        return scores;
    }
    public boolean isValidAnagram(String word) {
        return AnagramGenerator.isValidAnagram(word, anagram);
    }
}
