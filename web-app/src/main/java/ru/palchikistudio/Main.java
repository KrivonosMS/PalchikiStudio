package ru.palchikistudio;

import ru.palchikistudio.db.DbConfig;
import ru.palchikistudio.db.MySqlConnectionManagerImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Admin on 29.10.2018.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        DbConfig dbConfig = new DbConfig("db.properties");
        String query = "update palchiki_studio.tbl_master_events set img_name = '6.jpg' where id = 3";
        try(Connection connection = new MySqlConnectionManagerImpl(dbConfig).getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
            ps.executeUpdate();
        }
    }
}

