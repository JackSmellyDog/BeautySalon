package com.epam.salon.controller;

import com.epam.salon.model.Admin;
import com.epam.salon.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/test")
public class TemporaryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        UserService userService = new UserService();
//        List<Admin> list = userService.getAll();
//
//        PrintWriter writer = resp.getWriter();
//
//        StringBuilder builder = new StringBuilder();
//        for (Admin admin : list) {
//            builder.append(admin).append("<br>");
//        }
//
//        writer.println(builder.toString());
//        writer.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
