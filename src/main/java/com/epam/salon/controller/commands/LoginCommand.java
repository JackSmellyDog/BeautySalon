package com.epam.salon.controller.commands;

import com.epam.salon.model.User;
import com.epam.salon.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            UserService userService = new UserService();
            User user = userService.login(username, password);

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            forward("home");

        } catch (Exception e) {
            request.setAttribute("message", "Invalid data");
            forward("login");
        }
    }
}
