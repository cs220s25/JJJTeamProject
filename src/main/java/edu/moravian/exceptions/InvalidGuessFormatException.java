package edu.moravian.exceptions;

public class InvalidGuessFormatException extends RuntimeException {
    public InvalidGuessFormatException() {
        super("Invalid guess format.\nProper formatting is '!guess word'.");
    }
}
