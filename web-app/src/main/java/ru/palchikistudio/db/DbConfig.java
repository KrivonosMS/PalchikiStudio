package ru.palchikistudio.db;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Created by Admin on 31.10.2018.
 */
public class DbConfig {
    private String propertyFilePath;
    private String url;
    private String user;
    private String password;

    public DbConfig(String propertyFilePath) throws Exception {
        this.propertyFilePath = propertyFilePath;
        initConf();
    }

    private void initConf() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream input = classLoader.getResourceAsStream(propertyFilePath)) {
            throwExceptionIfNull(input);
            Properties properties = new Properties();
            properties.load(input);
            setUrl(properties.getProperty("url"));
            setUser(properties.getProperty("user"));
            setPassword(properties.getProperty("password"));
        } catch (IOException e) {
            throw new Exception("Ошибка при получении конфигурации БД.", e);
        }
    }

    private void throwExceptionIfNull(InputStream input) {
        if (input == null) {
            throw new IllegalArgumentException("Не удалось найти property-файл для БД");
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
