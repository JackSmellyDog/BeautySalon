package com.kpi.salon.controller.commands;

import com.kpi.salon.model.Request;
import com.kpi.salon.model.User;
import com.kpi.salon.services.IRequestService;
import com.kpi.salon.services.impl.RequestService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestPageCommand extends FrontCommand {
    private static final Logger LOGGER = Logger.getLogger(RequestPageCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        User user = (User) session.getAttribute("user");
        IRequestService requestService = new RequestService();

        List<Request> requests;

        if ("Admin".equals(role)) {
            requests = requestService.findAllRequests();
        } else if ("Master".equals(role)) {
            requests = requestService.findRequestsByMaster(user.getId());
        } else if ("Client".equals(role)) {
            requests = requestService.findRequestsByClient(user.getId());
        } else {
            requests = null;
        }

        if (requests != null) {
            session.setAttribute("requests", requests);

            String page = request.getParameter("page");
            request.setAttribute("page", (page == null)? 1 : page);

            forward("requests");
        } else {
            String username = (user == null)? "Anonymous" : user.getLogin();
            LOGGER.warn(String.format("%s has no rights to be here", username));

            forward("403");
        }
    }
}
