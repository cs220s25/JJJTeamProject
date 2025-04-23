package edu.moravian.APP;

import edu.moravian.DataStorage.DataManager;
import edu.moravian.DataStorage.InMemoryManager;
import edu.moravian.DataStorage.RedisManager;
import edu.moravian.DataStorage.Score;
import edu.moravian.exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;

public class GameController {
    private DataManager dataManager;
    private final ArrayList<String> players;
    private GameStatus gameStatus;
    private final String managerType;

    public GameController() {
        this.gameStatus = GameStatus.NOT_STARTED;
        this.players = new ArrayList<>();
        this.managerType = "redis";
    }

    public GameController(String managerType) {
        this.gameStatus = GameStatus.NOT_STARTED;
        this.players = new ArrayList<>();
        this.managerType = managerType;
    }

    public boolean playerNotRegistered(String playerName) {
        return !players.contains(playerName);
    }

    public String getAnagram(){
        return dataManager.getAnagram();
    }
    public GameStatus status(){
        return gameStatus;
    }
    public void guess(String word, String playerName) {
        dataManager.addPlayerWord(word, playerName);
    }

    public void checkWordIsValid(String word) {
        if (!dataManager.isValidWord(word)) {
            throw new InvalidWordException(word);
        }
        if (dataManager.isUsedWord(word)) {
            throw new WordAlreadyGuessedException(word);
        }
    }

    public void start() {
        gameStatus = GameStatus.STARTING;
    }

    public void join(String playerName) {
        players.add(playerName);
        if (players.size() == 2) {
            if (managerType.equals("redis")) {
                this.dataManager = new RedisManager(players.get(0), players.get(1));

            } else if (managerType.equals("inMemory")) {
                this.dataManager = new InMemoryManager(players.get(0), players.get(1));
            }
            gameStatus = GameStatus.IN_PROGRESS;
        }
    }

    public void stop() {
        gameStatus = GameStatus.NOT_STARTED;
        players.clear();
    }
    public String used() {
        String wordsUsed = dataManager.getWordsUsed();
        if (wordsUsed.equals("[]")) {
            return "No words used";
        }
        return wordsUsed;
    }
    public int numPlayers() {
        return players.size();
    }
    public String getScoreBoard() {
        StringBuilder scoreBoard = new StringBuilder();
        HashMap<String, Integer> playerScores = new HashMap<>();
        scoreBoard.append("Game Over!\n-+-+-+-+-+-+-+-+-\n");
        getPlayerScores(scoreBoard, playerScores);
        getWinner(playerScores, scoreBoard);
        return scoreBoard.toString();
    }
    private void getPlayerScores(StringBuilder scoreBoard, HashMap<String, Integer> playerScores) {
        for (String player : players) {
            scoreBoard.append(player).append(" scores: \n");
            ArrayList<Score> scores = dataManager.getPlayerScores(player);
            int totalScore = 0;
            for (Score score : scores) {
                totalScore += score.getScore();
                scoreBoard.append("  • ").append(score).append("\n");
            }
            scoreBoard.append("★ Total score: ").append(totalScore).append("\n-+-+-+-+-+-+-+-+-\n");
            playerScores.put(player, totalScore);
        }
    }

    private void getWinner(HashMap<String, Integer> playerScores, StringBuilder scoreBoard) {
        if (playerScores.get(players.get(0)) > playerScores.get(players.get(1))) {
            scoreBoard.append(players.getFirst()).append(" wins!");
        } else if (playerScores.get(players.get(0)) < playerScores.get(players.get(1))) {
            scoreBoard.append(players.get(1)).append(" wins!");
        } else {
            scoreBoard.append("It's a draw!");
        }
    }
}
