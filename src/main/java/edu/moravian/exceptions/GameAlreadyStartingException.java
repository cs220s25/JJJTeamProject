package edu.moravian.exceptions;

public class GameAlreadyStartingException extends RuntimeException
{
    public GameAlreadyStartingException(int numPlayersNeeded)
    {
        super("Game already starting, " + numPlayersNeeded + " players still needed.");
    }
}
