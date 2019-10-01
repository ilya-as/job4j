package ru.job4j.tdd;

public class InvalidKeyException extends Exception {
    public InvalidKeyException(String description) {
        super(description);
    }
}
