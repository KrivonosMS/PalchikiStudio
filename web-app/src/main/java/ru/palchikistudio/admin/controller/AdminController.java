package ru.palchikistudio.admin.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.palchikistudio.admin.core.MasterClassAdminService;
import ru.palchikistudio.admin.response.MasterClassResponse;
import ru.palchikistudio.admin.response.StandartAnswer;
import ru.palchikistudio.model.MasterClass;

import javax.servlet.ServletException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AdminController {
    public static final Logger LOGGER = Logger.getLogger(AdminController.class);
    private MasterClassAdminService masterClassService;

    @Autowired
    public void setMasterClassService(MasterClassAdminService masterClassService) {
        this.masterClassService = masterClassService;
    }

    @GetMapping(value = "/master_class")
    @ResponseBody
    public MasterClassResponse getMasterClasses(@RequestParam(value = "start") int from,
                                                @RequestParam(value = "limit") int limit) throws Exception {
        try {
            return masterClassService.getAllMasterClasses(from, limit);
        } catch (Exception e) {
            LOGGER.error("Непредвиденная ошибка.", e);
            return new MasterClassResponse("Непредвиденная ошибка.");
        }
    }

    @PostMapping(value = "/delete", headers ={"Content-Type=application/json"})
    @ResponseBody
    public StandartAnswer deleteMasterClasses(@RequestBody MasterClass masterClass) throws Exception {
        StringBuffer jb = new StringBuffer();
        Integer id = masterClass.getMasterClassId();
        try {
            if (id == null) {
                throw new IllegalArgumentException("Не удалось получить id мастер класса для удаления.");
            }
            masterClassService.setIsDeleted(id);
            return new StandartAnswer(true, "Мастер-класс успешно удален.");
        } catch (Exception e) {
            LOGGER.error("Непредвиденная ошибка.", e);
            return new StandartAnswer(false, "Непредвиденная ошибка.");
        }
    }

    @PostMapping(value = "/save", consumes = {"multipart/form-data"})
    @ResponseBody
    public StandartAnswer saveMasterClasses(@RequestParam("picture") MultipartFile file,
                                            @RequestParam("master_class_id") Integer id,
                                            @Valid @NotBlank @RequestParam("name") String name,
                                            @Valid @NotNull @RequestParam("coast") Integer coast,
                                            @Valid @NotNull @RequestParam("date") @DateTimeFormat(pattern="dd.MM.yyyy hh:mm") Date date,
                                            @RequestParam("description") String description,
                                            @RequestParam("teacher_name") String teacherName) throws Exception {
        try {
            String fileName = saveImage(file);
            MasterClass masterClass = new MasterClass
                    .Builder(name, coast, date)
                    .addMasterClassId(id)
                    .addDescription(description)
                    .addTeacherName(teacherName)
                    .addImgPath(fileName)
                    .build();
            LOGGER.info("MasterClass = " + masterClass.toString());
            masterClassService.saveMasterClass(masterClass);
            return new StandartAnswer(true, "Мастер-класс успешно сохранен.");
        } catch (Exception e) {
            LOGGER.error("Непредвиденная ошибка.", e);
            return new StandartAnswer(false, "Непредвиденная ошибка.");
        }
    }

    private String saveImage(MultipartFile file) throws IOException, ServletException {
        String fileName = file.getOriginalFilename();
        if (fileName != null && !"".equals(fileName)) {
            String curStringDate = new SimpleDateFormat("ddMMyyyy_HHmmss").format(System.currentTimeMillis());
            fileName = curStringDate + "-" + fileName;
            byte[] bytes = file.getBytes();
            String uploadFolder = System.getProperty("catalina.home") + "/webapps" + MasterClass.IMG_DIRECTORY;
            Path path = Paths.get(uploadFolder +  File.separator + fileName);
            Files.write(path, bytes);
        }
        return fileName;
    }
}
