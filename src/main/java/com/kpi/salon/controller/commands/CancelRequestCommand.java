package com.kpi.salon.controller.commands;

import com.kpi.salon.exceptions.RequestFailException;
import com.kpi.salon.model.Request;
import com.kpi.salon.model.User;
import com.kpi.salon.services.impl.RequestService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CancelRequestCommand extends FrontCommand {
    private static final Logger LOGGER = Logger.getLogger(CancelRequestCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            Long id = Long.parseLong(request.getParameter("id"));
            User user = (User) session.getAttribute("user");

            RequestService requestService = new RequestService();
            Request req = requestService.findRequestById(id);

            if (req != null && requestService.markAsCanceled(req)) {
                session.setAttribute("requests", requestService.findAllRequests());
                forward("requests");
            } else {
                throw new RequestFailException("Unable to change status");
            }

        } catch (NumberFormatException e) {
            LOGGER.error(String.format("Invalid request id. %s", e.getMessage()), e);
            forward("unknown");
        } catch (RequestFailException e) {
            LOGGER.error(e.getMessage(), e);
            forward("unknown");
        }
    }
}
