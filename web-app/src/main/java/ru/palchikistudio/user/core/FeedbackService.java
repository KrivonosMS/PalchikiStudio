package ru.palchikistudio.user.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 07.11.2018.
 */
public class FeedbackService {
    private final static String SUBJECT = "Сообщение из формы обратной связи сайта palchiki";
    private final static String PALCHIKI_EMAIL = "mihasic@inbox.ru";
    private final static String MAIL_PROPERTY_PATH = "mail.properties";

    public String createMessage(String name, String email, String phone, String msg) {
        String pattern = "Имя отправителя: %s.\nЭлектронный адрес отправителя: %s.\nТелефон отправителя:%s\n\n%s";
        return String.format(pattern, name, email, phone, msg);
    }

    public List<String> checkParams(String email, String phone, String bezSpama) {
        List<String> errors = new ArrayList<>();
        if ("".equals(email) & "".equals(phone)) {
            errors.add("Укажите телефон или email для связи.");
        }
        if (!"".equals(bezSpama)) {
            errors.add("Отправка не прошла проверку на спам.");
        }
        if (!"".equals(email) & isNotValidEmail(email)) {
            errors.add("Указан некорректный email");
        }
        return errors;
    }

    private boolean isNotValidEmail(String email) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(EmailServiceImpl.EMAIL_PATTERN);
        java.util.regex.Matcher m = p.matcher(email);
        return !m.matches();
    }

    public void sendInfoToEmail(String email, String phoneNumber, String name, String msg, FeedbackService feedbackService) throws EmailServiceException {
        String text = feedbackService.createMessage(name, email, phoneNumber, msg);
        EmailServiceImpl emailService = new EmailServiceImpl(MAIL_PROPERTY_PATH);
        emailService.send(SUBJECT, text, PALCHIKI_EMAIL);
    }
}
