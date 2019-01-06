package ru.palchikistudio.admin.controller;

import org.apache.log4j.Logger;
import ru.palchikistudio.admin.data.MasterClassAdminDaoImpl;
import ru.palchikistudio.db.DbConfig;
import ru.palchikistudio.db.MySqlConnectionManagerImpl;
import ru.palchikistudio.admin.core.MasterClassAdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by Admin on 17.12.2018.
 */
public class AdminMainPageController extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger(AdminMainPageController.class);
    private final String propertyFilePath= "db.properties";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbConfig dbConfig;
        try {
            int from = Integer.valueOf(req.getParameter("start"));
            int limit = Integer.valueOf(req.getParameter("limit"));
            dbConfig = new DbConfig(propertyFilePath);
            try(Connection connection = new MySqlConnectionManagerImpl(dbConfig).getConnection()) {
                MasterClassAdminServiceImpl masterClassService = new MasterClassAdminServiceImpl(new MasterClassAdminDaoImpl(connection));
                String answer = masterClassService.getAllMasterClasses(from, limit);
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