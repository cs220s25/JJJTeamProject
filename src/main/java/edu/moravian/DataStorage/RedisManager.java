package edu.moravian.DataStorage;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RedisManager implements DataManager {
    private Jedis jedis;
    private String anagram;
    public RedisManager(String player1, String player2) {
        try {
            jedis = new Jedis("localhost", 6379);
            System.out.println("Connection to server successfully");
            System.out.println("Server is running: " + jedis.ping());
            jedis.flushAll();

            // Initialize all data members in redis
            initializeLexiconData();
            initializePlayerTags(player1, player2);
            initializeScores();

            // Initialize anagram
            newAnagram();
        } catch (JedisConnectionException e) {
            System.out.println("Cannot Connect to Redis");
        }
    }
    private void initializeLexiconData(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("Files/large.txt"));
            String line = reader.readLine();
            while (line != null) {
                int wordLength = line.length();
                jedis.sadd(String.format("words:%d", wordLength), line);
                line = reader.readLine();
            }
            reader.close();
            jedis.del("words:2");
        } catch (IOException e) {
            System.out.println("Cannot read file");
        }
    }
    private void initializePlayerTags(String player1, String player2) {
        jedis.sadd("playerTags", player1);
        jedis.sadd("playerTags", player2);
    }

    private void initializeScores() {
        for (int i = 3; i < 19; i++) {
            jedis.hset("scores", String.valueOf(i), String.valueOf(i + 3));
        }
    }
    public boolean isValidWord(String word) {
        return jedis.sismember(String.format("words:%d", word.length()), word) && isValidAnagram(word);
    }

    public boolean isUsedWord(String word) {
        return jedis.sismember("totalGuessed", word);
    }
    public String getWordsUsed() {
        return jedis.smembers("totalGuessed").toString();
    }
    public void addPlayerWord(String word, String playerName) {
        jedis.sadd(String.format("guessed:%s", playerName), word);
        jedis.sadd("totalGuessed", word);
    }

    public void newAnagram() {
        ArrayList<String> words = new ArrayList<>(jedis.srandmember("words:6", 3));
        this.anagram = AnagramGenerator.generateAnagram(words);
    }

    public void setAnagram(String anagram) {
        this.anagram = anagram;
    }

    public String getAnagram() {
        return this.anagram;
    }

    public boolean isValidAnagram(String word) {
        return AnagramGenerator.isValidAnagram(word, this.anagram);
    }
    public ArrayList<Score> getPlayerScores(String playerName) {
        ArrayList<String> words = new ArrayList<>(jedis.smembers(String.format("guessed:%s", playerName)));
        ArrayList<Score> scores = new ArrayList<>();
        for (String word : words) {
            int value = Integer.parseInt(jedis.hget("scores", String.valueOf(word.length())));
            scores.add(new Score(word, value));
        }
        return scores;
    }
}
