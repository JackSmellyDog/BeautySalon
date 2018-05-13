package com.epam.salon.controller.commands;

import com.epam.salon.exceptions.SuchUserIsExistException;
import com.epam.salon.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import java.io.IOException;

public class RegisterCommand extends FrontCommand {
    private static final Logger LOGGER = Logger.getLogger(RegisterCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            try {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String confirmPassword = request.getParameter("confirmPassword");

                UserService userService = new UserService();

                userService.register(username, password, confirmPassword);
            } catch (SuchUserIsExistException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            forward("registration");
        }
    }
}
