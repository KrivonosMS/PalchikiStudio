package ru.palchikistudio.user.controller;

import org.apache.log4j.Logger;
import ru.palchikistudio.user.data.MasterClassUserDaoImpl;
import ru.palchikistudio.db.DbConfig;
import ru.palchikistudio.db.MySqlConnectionManagerImpl;
import ru.palchikistudio.user.core.MasterClassUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by Admin on 04.11.2018.
 */
public class AnnouncementController extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger(AnnouncementController.class);
    private final String propertyFilePath= "db.properties";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbConfig dbConfig;
        try {
            dbConfig = new DbConfig(propertyFilePath);
            try(Connection connection = new MySqlConnectionManagerImpl(dbConfig).getConnection()) {
                MasterClassUserServiceImpl masterClassService = new MasterClassUserServiceImpl(new MasterClassUserDaoImpl(connection));
                String answer = masterClassService.getAllActualMasterClasses();
                sendAnswer(resp, answer);
            }
        } catch (Exception e) {
            LOGGER.error("Непредвиденная ошибка.", e);
            resp.sendError(500);
        }
    }

    private void sendAnswer(HttpServletResponse resp, String answer) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(answer);
    }
}
