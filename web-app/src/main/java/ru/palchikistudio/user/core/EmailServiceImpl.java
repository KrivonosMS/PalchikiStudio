package ru.palchikistudio.user.core;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Admin on 06.11.2018.
 */
public class EmailServiceImpl implements EmailService {
    public final static  String EMAIL_PATTERN = "[^@]+@[^@]+\\.[a-zA-Z]{2,6}";
    private String email;
    private String password;
    private Properties props;

    public EmailServiceImpl(String hostname, String port, String email, String password) {
        initParams(hostname, port, email, password);
    }

    public EmailServiceImpl(String propertyFilePath) throws EmailServiceException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream input = classLoader.getResourceAsStream(propertyFilePath)) {
            throwExceptionIfNull(input);
            Properties props = new Properties();
            props.load(input);
            initParams(props.getProperty("hostname"), props.getProperty("port"), props.getProperty("email"), props.getProperty("password"));
        } catch (IOException e) {
            throw new EmailServiceException("Ошибка при получении конфигурирование сервиса отправки писем.", e);
        }
    }

    private void initParams(String hostname, String port, String email, String password) {
        this.email = email;
        this.password = password;

        props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", hostname);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
    }

    private void throwExceptionIfNull(InputStream input) {
        if (input == null) {
            throw new IllegalArgumentException("Не удалось найти property-файл для сервиса отправки писем.");
        }
    }

    @Override
    public void send(String subject, String text, String toEmail) throws EmailServiceException {
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new EmailServiceException("Ошибка при отправке сообщения на почту.", e);
        }
    }
}
