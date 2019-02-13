package ru.palchikistudio;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.palchikistudio.db.DbConfig;
import ru.palchikistudio.db.MySqlConnectionManagerImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Admin on 29.10.2018.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("1");
        DbConfig dbConfig = new DbConfig("db.properties");
        String password = new BCryptPasswordEncoder().encode("admin");
        System.out.println(password);
        String query = "insert into users(username, password, role, enable) values('admin', '"+password+"', 'ROLE_ADMIN', 1)";
        try(Connection connection = new MySqlConnectionManagerImpl(dbConfig).getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
            ps.executeUpdate();
        }
    }
}

