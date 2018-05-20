package com.kpi.salon.controller.commands;

import com.kpi.salon.model.User;
import com.kpi.salon.services.IRequestService;
import com.kpi.salon.services.impl.RequestService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RequestPageCommand extends FrontCommand {
    private static final Logger LOGGER = Logger.getLogger(RequestPageCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        User user = (User) session.getAttribute("user");

        IRequestService requestService = new RequestService();

        if ("Admin".equals(role)) {

            if (session.getAttribute("requests") == null) {
                session.setAttribute("requests", requestService.findAllRequests());
            }

            forward("requests");

        } else if ("Master".equals(role)) {

            if (session.getAttribute("requests") == null) {
                session.setAttribute("requests", requestService.findRequestsByMaster(user.getId()));
            }

            forward("requests");
        } else if ("Client".equals(role)) {

            if (session.getAttribute("requests") == null) {
                session.setAttribute("requests", requestService.findRequestsByClient(user.getId()));
            }

            forward("requests");
        } else {
            String username = (user == null)? "Anonymous" : user.getLogin();
            LOGGER.warn(String.format("%s has no rights to be here", username));

            forward("403");
        }
    }
}
