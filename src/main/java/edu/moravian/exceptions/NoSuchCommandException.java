package edu.moravian.exceptions;

public class NoSuchCommandException extends RuntimeException
{
    public NoSuchCommandException(String command)
    {
        super("No such command: " + command);
    }
}
