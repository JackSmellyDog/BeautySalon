package com.epam.salon.controller.commands;

import com.epam.salon.exceptions.SuchUserIsExistException;
import com.epam.salon.services.UserService;

import javax.servlet.ServletException;
import java.io.IOException;

public class RegisterCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            try {
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                UserService userService = new UserService();

                userService.register(username, password);
            } catch (SuchUserIsExistException e) {
                e.printStackTrace();
            }
        } else {
            forward("registration");
        }
    }
}
