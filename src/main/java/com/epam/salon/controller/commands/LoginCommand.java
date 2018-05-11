package com.epam.salon.controller.commands;

import com.epam.salon.model.Admin;
import com.epam.salon.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");


        UserService userService = new UserService();
        Admin admin = userService.findByUsername(username);


        if (admin != null && admin.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            //request.getRequestDispatcher("listProducts.jsp").forward(request, response);
            forward("home");
        } else {
            request.setAttribute("message", "Invalid data");
            //request.getRequestDispatcher("login.jsp").forward(request, response);
            forward("test");
        }

    }
}
