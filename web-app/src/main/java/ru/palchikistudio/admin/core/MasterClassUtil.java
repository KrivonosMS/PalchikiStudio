package ru.palchikistudio.admin.core;

import org.springframework.web.multipart.MultipartFile;
import ru.palchikistudio.model.MasterClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 28.12.2018.
 */
public class MasterClassUtil {

    public static String saveFile(MultipartFile file, Integer id) throws IOException {
        String relativePath = null;
        boolean isNewMasterClass = id == null;
        String fileName = file.getOriginalFilename();
        if (fileName != null && !"".equals(fileName)) {
            String curStringDate = new SimpleDateFormat("ddMMyyyy_HHmmss").format(System.currentTimeMillis());
            fileName = curStringDate + "-" + fileName;
            relativePath = MasterClass.IMG_DIRECTORY + File.separator + fileName;
            String rootFolder = System.getProperty("catalina.home") + "/webapps";
            Path path = Paths.get(rootFolder + relativePath);
            byte[] bytes = file.getBytes();
            Files.write(path, bytes);
        } else if(isNewMasterClass){
            relativePath = MasterClass.IMG_DIRECTORY + File.separator + MasterClass.DEFAULT_IMG;
        }
        return relativePath;
    }

    public static String getDefaultImgPath() {
        return MasterClass.IMG_DIRECTORY + File.separator + MasterClass.DEFAULT_IMG;
    }
}
