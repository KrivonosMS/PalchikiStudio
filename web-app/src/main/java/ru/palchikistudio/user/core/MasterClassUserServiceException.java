package ru.palchikistudio.user.core;

/**
 * Created by Admin on 04.11.2018.
 */
public class MasterClassUserServiceException extends Exception {
    public MasterClassUserServiceException() {
        super();
    }

    public MasterClassUserServiceException(String message) {
        super(message);
    }

    public MasterClassUserServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MasterClassUserServiceException(Throwable cause) {
        super(cause);
    }
}
