package ru.palchikistudio.user.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Admin on 28.10.2018.
 */
public class ContactController extends HttpServlet{
    public  final String CONTACT_PAGE ="WEB-INF/views/contacts.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(CONTACT_PAGE).forward(req, resp);
    }
}
