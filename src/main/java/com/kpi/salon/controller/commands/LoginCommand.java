package com.kpi.salon.controller.commands;

import com.kpi.salon.model.User;
import com.kpi.salon.services.impl.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand extends FrontCommand {
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            UserService userService = new UserService();
            User user = userService.login(username, password);

            // TODO code-verification for Admins

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            String role = user.getClass().getSimpleName();
            session.setAttribute("role", role);

            LOGGER.info(String.format("User %s has logged in", user.getLogin()));
            forward("home");

        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);

            request.setAttribute("message", "Invalid data");
            forward("login");
        }
    }
}
