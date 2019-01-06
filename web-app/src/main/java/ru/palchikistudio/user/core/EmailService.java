package ru.palchikistudio.user.core;

/**
 * Created by Admin on 07.11.2018.
 */
public interface EmailService {
    public void send(String subject, String text, String toEmail) throws EmailServiceException;

}
