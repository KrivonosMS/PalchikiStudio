package ru.palchikistudio.admin.controller;

/**
 * Created by Admin on 29.12.2018.
 */
public class TransformationException extends Exception{
    public TransformationException() {
        super();
    }

    public TransformationException(String message) {
        super(message);
    }

    public TransformationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransformationException(Throwable cause) {
        super(cause);
    }

    protected TransformationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
