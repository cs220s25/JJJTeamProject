package edu.moravian.exceptions;

public class GameNotStartedException extends RuntimeException
{
    public GameNotStartedException()
    {
        super("Game not started");
    }
}
