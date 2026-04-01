package ru.yandex.practicum;

public class DictionaryLoadingException extends Exception {
    public DictionaryLoadingException(String message) {
        super(message);
    }

    public DictionaryLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}

