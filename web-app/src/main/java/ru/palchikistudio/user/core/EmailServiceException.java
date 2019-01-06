package ru.palchikistudio.user.core;

/**
 * Created by Admin on 06.11.2018.
 */
public class EmailServiceException extends Exception {
    public EmailServiceException() {
        super();
    }

    public EmailServiceException(String message) {
        super(message);
    }

    public EmailServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailServiceException(Throwable cause) {
        super(cause);
    }
}
