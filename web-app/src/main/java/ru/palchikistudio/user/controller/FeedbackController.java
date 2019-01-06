package ru.palchikistudio.user.controller;

import org.apache.log4j.Logger;
import ru.palchikistudio.user.core.FeedbackService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Admin on 06.11.2018.
 */
public class FeedbackController extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger(FeedbackController.class);
    private FeedbackService feedbackService;

    @Override
    public void init() throws ServletException {
        feedbackService = new FeedbackService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("tel");
        String name = req.getParameter("name");
        String msg = req.getParameter("message");
        String bezSpama = req.getParameter("bezspama");

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try(PrintWriter writer = resp.getWriter()) {
            FeedbackService feedbackService = new FeedbackService();
            List<String> errors = feedbackService.checkParams(email, phoneNumber, bezSpama);
            if(errors.isEmpty()) {
                feedbackService.sendInfoToEmail(email, phoneNumber, name, msg, feedbackService);
                writer.write("Мы с Вами свяжемся в ближайшее время.");
            } else {
                writer.write(
                        errors.stream().collect(Collectors.joining("<p>"))
                );
            }
        } catch (Exception e) {
            LOGGER.error("Непредвиденная ошибка.", e);
            resp.getWriter().write("Отправка сообщения не удалась. Попробуйте еще раз.");
        }
    }
}
