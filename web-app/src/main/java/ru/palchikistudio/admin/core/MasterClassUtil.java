package ru.palchikistudio.admin.core;

import ru.palchikistudio.model.MasterClass;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 28.12.2018.
 */
public class MasterClassUtil {
    public static Integer transformToInteger(String value) throws Exception {
        Integer transformedValue = null;
        if(value != null && !"".equals(value)) {
            try {
                transformedValue = Integer.valueOf(value);
            } catch (Exception e) {
                throw new Exception("Ошибка во время преобразования строки в число.", e);
            }
        }
        return transformedValue;
    }

    public static Date transformToDate(String value) throws Exception {
        Date transformedValue = null;
        if (value != null && !"".equals(value)) {
            try {
                DateFormat format = new SimpleDateFormat(MasterClass.DATE_FORMAT);
                transformedValue = format.parse(value);
            } catch (ParseException e) {
                throw new Exception("Ошибка во время преобразования строки в дату.", e);
            }
        }
        return transformedValue;
    }

    public static void checkOnEmpty(String name, Integer coast, Date date) throws Exception {
        List<String> errors = new ArrayList<>();
        if (name == null || "".equals(name)) {
            errors.add("Не задано название мастер-класса.");
        }
        if (coast == null) {
            errors.add("Не задана стоимость мастер-класса.");
        }
        if (date == null) {
            errors.add("Не задана дата проведения мастер-класса");
        }
        if (!errors.isEmpty()) {
            throw new Exception(errors.toString());
        }
    }
}
