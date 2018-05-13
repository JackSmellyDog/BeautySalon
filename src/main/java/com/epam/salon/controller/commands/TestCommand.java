package com.epam.salon.controller.commands;

import com.epam.salon.model.Admin;
import com.epam.salon.services.UserService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TestCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        response.setContentType("text/html");
        UserService userService = new UserService();
        List<Admin> list = userService.getAllAdmins();

        PrintWriter writer = response.getWriter();

        StringBuilder builder = new StringBuilder();
        for (Admin admin : list) {
            builder.append(admin).append("<br>");
        }

        writer.println(builder.toString());
        writer.close();

    }
}
