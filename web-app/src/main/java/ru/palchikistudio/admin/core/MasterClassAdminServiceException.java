package ru.palchikistudio.admin.core;

/**
 * Created by Admin on 04.11.2018.
 */
public class MasterClassAdminServiceException extends Exception {
    public MasterClassAdminServiceException() {
        super();
    }

    public MasterClassAdminServiceException(String message) {
        super(message);
    }

    public MasterClassAdminServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MasterClassAdminServiceException(Throwable cause) {
        super(cause);
    }
}
