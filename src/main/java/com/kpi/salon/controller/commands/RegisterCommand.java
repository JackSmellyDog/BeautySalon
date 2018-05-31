package com.kpi.salon.controller.commands;

import com.kpi.salon.exceptions.SuchUserIsExistException;
import com.kpi.salon.model.Client;
import com.kpi.salon.services.impl.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterCommand extends FrontCommand {
    private static final Logger LOGGER = Logger.getLogger(RegisterCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            try {

                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String confirmPassword = request.getParameter("confirmPassword");

                UserService userService = new UserService();

                if (userService.register(username, password, confirmPassword)) {
                    Client client = (Client) userService.login(username, password);
                    session.setAttribute("user", client);
                    session.setAttribute("role", client.getClass().getSimpleName());
                    session.setAttribute("lastCommand", "HomePageCommand");
                    forward("home");
                }

            } catch (SuchUserIsExistException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            session.setAttribute("lastCommand", getClass().getSimpleName());
            forward("registration");
        }
    }
}
