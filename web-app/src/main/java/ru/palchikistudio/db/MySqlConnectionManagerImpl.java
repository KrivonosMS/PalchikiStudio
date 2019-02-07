package ru.palchikistudio.db;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 29.10.2018.
 */
@Component
public class MySqlConnectionManagerImpl implements ConnectionManager {
    private final String className = "com.mysql.jdbc.Driver";
    private final String url;
    private final String user;
    private final String password;

    public MySqlConnectionManagerImpl(String url, String user, String password) {
        checkByNull(url, user, password);
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public MySqlConnectionManagerImpl(DbConfig dbConfig) {
        this(dbConfig.getUrl(), dbConfig.getUser(), dbConfig.getPassword());
    }

    public MySqlConnectionManagerImpl() throws Exception {
        this(new DbConfig("db.properties"));
    }

    private void checkByNull(String url, String user, String password) {
        List<String> errorMessages = new ArrayList<>();
        if (url == null || "".endsWith(url)) {
            errorMessages.add("Не задан url для БД");
        }
        if (user == null || "".endsWith(user)) {
            errorMessages.add("Не задан пользователь БД");
        }
        if (password == null || "".endsWith(password)) {
            errorMessages.add("Не задан парольл пользователя БД");
        }
        if (errorMessages.size() > 0) {
            throw new IllegalArgumentException(errorMessages.toString());
        }
    }

    @Override
    public Connection getConnection() throws Exception {
        try {
            Class.forName(className).newInstance();
            return DriverManager.getConnection(url, user, password);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            throw new Exception("Ошибка получения соединения с БД");
        }
    }

    @Override
    public void close(Connection connection) throws SQLException {
        if(connection != null) {
            connection.close();
        }
    }
}
