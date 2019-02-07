package ru.palchikistudio.user.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.palchikistudio.admin.core.MasterClassAdminService;
import ru.palchikistudio.db.DbConfig;
import ru.palchikistudio.db.MySqlConnectionManagerImpl;
import ru.palchikistudio.model.MasterClass;
import ru.palchikistudio.user.core.FeedbackService;
import ru.palchikistudio.user.core.MasterClassUserService;
import ru.palchikistudio.user.core.MasterClassUserServiceImpl;
import ru.palchikistudio.user.data.MasterClassUserDaoImpl;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    public static final Logger LOGGER = Logger.getLogger(UserController.class);
    private MasterClassUserService masterClassService;

    @Autowired
    public void setMasterClassService(MasterClassUserService masterClassService) {
        this.masterClassService = masterClassService;
    }

    @GetMapping(value = "/gallery")
    public String getGallery(Model model){
        return "gallery";
    }

    @GetMapping(value = "/contacts")
    public String getContacts(Model model){
        return "contacts";
    }

    @GetMapping(value = "/forecast")
    public String getForecast(Model model){
        return "forecast";
    }

    @GetMapping(value = "/mock")
    public String getMockPage(Model model){
        return "mock";
    }

    @GetMapping(value = "/announcement")
    @ResponseBody
    public List<MasterClass> getAnnouncement() throws Exception {
        try {
            return  masterClassService.getAllActualMasterClasses();
        } catch (Exception e) {
            LOGGER.error("Непредвиденная ошибка.", e);
            throw e;
        }
    }

    @PostMapping(value = "/feedback", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String sendFeedbackMail(@RequestParam(value = "email") String email,
                                   @RequestParam(value = "tel") String phoneNumber,
                                   @RequestParam(value = "message") String msg,
                                   @RequestParam(value = "name") String name,
                                   @RequestParam(value = "bezspama") String bezSpama){
        try {
            FeedbackService feedbackService = new FeedbackService();
            List<String> errors = feedbackService.checkParams(email, phoneNumber, bezSpama);
            if(errors.isEmpty()) {
                feedbackService.sendInfoToEmail(email, phoneNumber, name, msg, feedbackService);
                return "Мы с Вами свяжемся в ближайшее время.";
            } else {
                return errors.stream().collect(Collectors.joining("<p>")
                );
            }
        } catch (Exception e) {
            LOGGER.error("Непредвиденная ошибка.", e);
            return "Отправка сообщения не удалась. Попробуйте еще раз.";
        }
    }
}
