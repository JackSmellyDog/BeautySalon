package com.kpi.salon.controller.commands;

import com.kpi.salon.services.impl.UserService;

import javax.servlet.ServletException;
import java.io.IOException;

public class DeleteMasterCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        //TODO validation
        Long id = Long.parseLong(request.getParameter("id"));
        UserService userService = new UserService();
        userService.deleteMaster(id);

        request.getSession().setAttribute("masters", userService.findAllMasters());
        forward("masters");
    }
}
