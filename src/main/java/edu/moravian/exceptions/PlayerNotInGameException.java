package edu.moravian.exceptions;

public class PlayerNotInGameException extends RuntimeException
{
    public PlayerNotInGameException(String player)
    {
        super("Player not in game: " + player);
    }
}
