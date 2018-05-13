package com.epam.salon.controller.commands;

import com.epam.salon.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MasterPageCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("masters") == null) {
            UserService userService = new UserService();
            session.setAttribute("masters", userService.getAllMasters());
        }
        forward("masters");
    }
}
