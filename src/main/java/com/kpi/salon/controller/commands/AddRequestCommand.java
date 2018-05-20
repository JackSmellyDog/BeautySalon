package com.kpi.salon.controller.commands;

import com.kpi.salon.model.User;
import com.kpi.salon.services.impl.RequestService;
import com.kpi.salon.services.impl.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddRequestCommand extends FrontCommand {
    private static final Logger LOGGER = Logger.getLogger(AddRequestCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserService userService = new UserService();

        if ("POST".equalsIgnoreCase(request.getMethod())){
            RequestService requestService = new RequestService();

            User user = (User) session.getAttribute("user");
            Long clientId = user.getId();

            String masterId = request.getParameter("master_id");
            String date = request.getParameter("date");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

            if (requestService.create(dateTime, clientId, Long.parseLong(masterId))) {
                forward("newrequest");
            } else {
                forward("unknown");
            }


        } else {
            if (session.getAttribute("masters") == null) {
                session.setAttribute("masters", userService.findAllMasters());
            }
            forward("newrequest");
        }
    }
}
