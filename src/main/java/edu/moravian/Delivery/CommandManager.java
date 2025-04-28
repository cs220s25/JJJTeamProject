package edu.moravian.Delivery;

import edu.moravian.APP.GameController;
import edu.moravian.APP.GameStatus;
import edu.moravian.exceptions.*;

public class CommandManager {
    private final GameController gameController;
    CommandManager() {
        gameController = new GameController();
    }

    CommandManager(GameController gameController) {
        this.gameController = gameController;
    }

    public String executeCommand(String cmdSignature, String playerName) {
        String message = "";
        if (gameController.playerNotRegistered(playerName) && gameController.status() == GameStatus.IN_PROGRESS) {
            throw new PlayerNotInGameException(playerName);
        }
        if (gameController.status() == GameStatus.IN_PROGRESS){
            message = "\nAnagram: " + gameController.getAnagram();
        }
        if (cmdSignature.startsWith("guess")) {
            return guess(cmdSignature, playerName) + message;
        }
        return switch (cmdSignature) {
            case "start" -> start();
            case "status" -> "Status: `" + status().toString() + "`" + message;
            case "join" -> join(playerName) + message;
            case "stop" -> stop();
            case "used" -> used() + message;
            case "help" -> help() + message;
            default -> throw new NoSuchCommandException(cmdSignature);
        };
    }
    public String guess(String cmdSignature, String playerName) {
        if (gameController.status() != GameStatus.IN_PROGRESS) {
            throw new GameNotStartedException();
        }
        String[] parts = cmdSignature.split(" ");
        if (parts.length != 2) {
            throw new InvalidGuessFormatException();
        }
        String word = parts[1];
        gameController.checkWordIsValid(word);
        gameController.guess(word, playerName);
        return "Correct: " + word + " is a valid word!";
    }
    public String help() {
        return """
                    Valid commands:
                    • `!start`: start the game
                    • `!join`: join the game
                    • `!guess <word>`: guess a word
                    • `!stop`: stop the game
                    • `!used`: get used words
                    • `!status`: get game status
                    • `!help`: displays commands.""";
    }

    public String used() {
        if (gameController.status() != GameStatus.IN_PROGRESS) {
            throw new GameNotStartedException();
        }
        return gameController.used();
    }
    public String join(String playerName) {
        if (gameController.status() == GameStatus.NOT_STARTED) {
            throw new GameNotStartedException();
        } else if (gameController.status() == GameStatus.IN_PROGRESS) {
            throw new GameInProgressException();
        }
        if (!gameController.playerNotRegistered(playerName)) {
            throw new PlayerAlreadyInGameException(playerName);
        }
        if (gameController.numPlayers() < 2){
            gameController.join(playerName);
            if (gameController.status() == GameStatus.IN_PROGRESS) {
                return "Game started!\nAnagram: " + gameController.getAnagram();
            }
            return "Player " + playerName + " joined the game.";
        }
        return "Game not started.";
    }
    public String start() {
        if (gameController.status() == GameStatus.IN_PROGRESS) {
            throw new GameInProgressException();
        }
        else if (gameController.status() == GameStatus.STARTING) {
            int numPlayers = 2 - gameController.numPlayers();
            throw new GameAlreadyStartingException(numPlayers);
        }
        gameController.start();
        return "Game starting, type `!join` to join.";
    }
    public String stop() {
        if (gameController.status() == GameStatus.NOT_STARTED) {
            throw new GameNotStartedException();
        } else if (gameController.status() == GameStatus.STARTING) {
            gameController.stop();
            return "Game stopped";
        } else {
            String scoreBoard = gameController.getScoreBoard();
            gameController.stop();
            return scoreBoard;
        }
    }
    public GameStatus status() {
        return gameController.status();
    }

    public String getAnagram() {
        return gameController.getAnagram();
    }
}
