package com.kpi.salon.controller.commands;

import com.kpi.salon.model.User;
import com.kpi.salon.services.impl.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MasterPageCommand extends FrontCommand {
    private static final Logger LOGGER = Logger.getLogger(MasterPageCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (role == null || "Admin".equals(role) || "Client".equals(role)) {
            if (session.getAttribute("masters") == null) {
                UserService userService = new UserService();
                session.setAttribute("masters", userService.findAllMasters());
            }

            forward("masters");

        } else {
            User user = (User) session.getAttribute("user");
            String username = (user == null)? "Anonymous" : user.getLogin();

            LOGGER.warn(String.format("%s has no rights to be here", username));
            forward("403");
        }
    }
}
