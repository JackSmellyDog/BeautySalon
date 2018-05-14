package com.kpi.salon.controller.commands;

import com.kpi.salon.exceptions.SuchUserIsExistException;
import com.kpi.salon.services.impl.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import java.io.IOException;

public class AddMasterCommand extends FrontCommand {
    private static final Logger LOGGER = Logger.getLogger(AddMasterCommand.class);

    private UserService userService = new UserService();

    @Override
    public void process() throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String name = request.getParameter("last_first_name");
            String description = request.getParameter("description");

            // add boolean
            userService.addMaster(username, password, confirmPassword, name, description);
            request.getSession().setAttribute("masters", userService.findAllMasters());
            forward("masters");

        } catch (SuchUserIsExistException e) {
            LOGGER.error(e.getMessage(), e);
            //add message
            forward("masters");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            forward("masters");
        }

    }
}
