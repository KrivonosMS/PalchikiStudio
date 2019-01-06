package ru.palchikistudio.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Admin on 29.10.2018.
 */
public interface ConnectionManager {
    public Connection getConnection() throws Exception;
    public void close(Connection connection) throws SQLException;
}

