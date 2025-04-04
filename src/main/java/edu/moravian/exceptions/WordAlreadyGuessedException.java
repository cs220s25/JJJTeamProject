package edu.moravian.exceptions;

public class WordAlreadyGuessedException extends RuntimeException
{
    public WordAlreadyGuessedException(String word)
    {
        super("Word already guessed: " + word);
    }

    public static class PlayerAlreadyInGameException extends RuntimeException
    {
        public PlayerAlreadyInGameException(String player)
        {
            super("Player " + player + " is already in the game");
        }
    }
}
