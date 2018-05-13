package com.epam.salon.controller.commands;

import com.epam.salon.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MasterPageCommand extends FrontCommand {
    private static final Logger LOGGER = Logger.getLogger(MasterPageCommand.class);

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
