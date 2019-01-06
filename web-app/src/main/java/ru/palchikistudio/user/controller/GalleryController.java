package ru.palchikistudio.user.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Admin on 25.10.2018.
 */
public class GalleryController extends HttpServlet{
    public final String GALLERY_PAGE ="WEB-INF/views/gallery.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(GALLERY_PAGE).forward(req, resp);
    }
}
