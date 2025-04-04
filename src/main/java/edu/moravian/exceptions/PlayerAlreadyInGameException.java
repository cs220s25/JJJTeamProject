package edu.moravian.exceptions;

public class PlayerAlreadyInGameException extends RuntimeException {
    public PlayerAlreadyInGameException(String playerName) {
        super("Player already in game " + playerName);
    }
}
