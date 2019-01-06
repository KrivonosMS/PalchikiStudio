package ru.palchikistudio.admin.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import ru.palchikistudio.admin.core.MasterClassAdminService;
import ru.palchikistudio.admin.core.MasterClassAdminServiceImpl;
import ru.palchikistudio.admin.core.MasterClassUtil;
import ru.palchikistudio.admin.data.MasterClassAdminDaoImpl;
import ru.palchikistudio.admin.response.StandartAnswer;
import ru.palchikistudio.db.DbConfig;
import ru.palchikistudio.db.MySqlConnectionManagerImpl;
import ru.palchikistudio.model.MasterClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Admin on 27.12.2018.
 */
@MultipartConfig(
        maxFileSize=1024*1024*5,
        maxRequestSize=1024*1024*6
)
public class AdminSaveMasterClassController extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger(AdminSaveMasterClassController.class);
    private final String propertyFilePath= "db.properties";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            Integer id = getId(req, resp);
            LOGGER.info("id = " + id);
            String name = req.getParameter("name");
            LOGGER.info("name = " + name);
            Integer coast = getCoast(req, resp);
            LOGGER.info("coast= " + coast);
            Date date = getDate(req, resp);
            LOGGER.info("date = " + date.toString());
            MasterClassUtil.checkOnEmpty(name, coast, date);
            String description = req.getParameter("description");
            LOGGER.info("description = " + description);
            String teacherName = req.getParameter("teacher_name");
            LOGGER.info("teacher = " + teacherName);
            String fileName = saveImage(req);
            LOGGER.info("file = " + fileName);
            MasterClass masterClass = new MasterClass
                    .Builder(name, coast, date)
                    .addMasterClassId(id)
                    .addDescription(description)
                    .addTeacherName(teacherName)
                    .addImgPath(fileName)
                    .build();
            DbConfig dbConfig = new DbConfig(propertyFilePath);
            try(Connection connection = new MySqlConnectionManagerImpl(dbConfig).getConnection()) {
                MasterClassAdminService masterClassService =
                        new MasterClassAdminServiceImpl(new MasterClassAdminDaoImpl(connection));
                String answer = masterClassService.saveMasterClass(masterClass);
                sendAnswer(resp, answer);
            }
        } catch (Exception e) {
            LOGGER.error("Непредвиденная ошибка.", e);
            Gson gson = new GsonBuilder().create();
            String answer = gson.toJson(new StandartAnswer(false, e.getMessage()));
            sendAnswer(resp, answer);
        }
    }

    private String saveImage(HttpServletRequest req) throws IOException, ServletException {
        Part part = req.getPart("picture");
        String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        if (fileName != null && !"".equals(fileName)) {
            String curStringDate = new SimpleDateFormat("ddMMyyyy_HHmmss").format(System.currentTimeMillis());
            fileName = curStringDate + "-" + fileName;
            String applicationPath = req.getServletContext().getRealPath("");
            String uploadFilePath = applicationPath + File.separator + MasterClass.IMG_DIRECTORY;
            File fileSaveDir = new File(uploadFilePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
            part.write(uploadFilePath + File.separator + fileName);
        } else {
            fileName = "default.jpg";
        }
        return fileName;
    }

    private Date getDate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            return MasterClassUtil.transformToDate(req.getParameter("date"));
        } catch (Exception e) {
            String errorMessage = "Некорректное значение даты проведения мастер-класса.";
            throw new Exception(errorMessage, e);
        }
    }

    private Integer getCoast(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            return MasterClassUtil.transformToInteger(req.getParameter("coast"));
        } catch (Exception e) {
            String errorMessage = "Некорректное значение стоимости мастер-класса.";
            throw new Exception(errorMessage, e);
        }
    }

    private Integer getId(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            return MasterClassUtil.transformToInteger(req.getParameter("master_class_id"));
        } catch (Exception e) {
            String errorMessage = "Некорректное значение id мастер-класса.";
            throw new Exception(errorMessage, e);
        }
    }

    private void sendAnswer(HttpServletResponse resp, String answer) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(answer);
    }
}
