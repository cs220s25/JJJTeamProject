package edu.moravian.exceptions;

public class InvalidWordException extends RuntimeException
{
    public InvalidWordException(String word)
    {
        super("Invalid word: " + word);
    }
}
