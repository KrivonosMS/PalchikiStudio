package ru.palchikistudio.admin.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.Logger;
import ru.palchikistudio.admin.core.MasterClassAdminServiceImpl;
import ru.palchikistudio.admin.data.MasterClassAdminDaoImpl;
import ru.palchikistudio.db.DbConfig;
import ru.palchikistudio.db.MySqlConnectionManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by Admin on 25.12.2018.
 */
public class AdminDeleteMasterClassController extends HttpServlet{
    public static final Logger LOGGER = Logger.getLogger(AdminDeleteMasterClassController.class);
    private final String propertyFilePath= "db.properties";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbConfig dbConfig;
        StringBuffer jb = new StringBuffer();
        String line;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(jb.toString());
            JsonObject rootObject = jsonElement.getAsJsonObject();
            JsonElement masterClassId = rootObject.get("master_class_id");
            int id;
            if (masterClassId != null) {
                id = masterClassId.getAsInt();
            } else {
                throw new IllegalArgumentException("Не удалось получить id мастер класса для удаления.");
            }
            dbConfig = new DbConfig(propertyFilePath);
            try(Connection connection = new MySqlConnectionManagerImpl(dbConfig).getConnection()) {
                MasterClassAdminServiceImpl masterClassService =
                        new MasterClassAdminServiceImpl(new MasterClassAdminDaoImpl(connection));
                String answer = masterClassService.setIsDeleted(id);
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
