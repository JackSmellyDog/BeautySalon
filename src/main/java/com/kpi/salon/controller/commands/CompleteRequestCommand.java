package com.kpi.salon.controller.commands;

import com.kpi.salon.exceptions.RequestFailException;
import com.kpi.salon.model.Request;
import com.kpi.salon.model.User;
import com.kpi.salon.services.impl.EmailService;
import com.kpi.salon.services.impl.RequestService;
import com.kpi.salon.services.impl.ReviewService;
import com.kpi.salon.services.impl.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CompleteRequestCommand extends FrontCommand {
    private static final Logger LOGGER = Logger.getLogger(CompleteRequestCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            Long id = Long.parseLong(request.getParameter("id"));
            User user = (User) session.getAttribute("user");

            ReviewService reviewService = new ReviewService();
            RequestService requestService = new RequestService();

            Request req = requestService.findRequestById(id);

            if (req != null && reviewService.create("", 0, req)) {

                if (!requestService.markAsDone(req)){
                    throw new RequestFailException("Unable to change status");
                }

                EmailService emailService = new EmailService();
                String subject = "Leave a review";
                String message = String.format("http://localhost:8080/app?command=AddReview&id=%d", id);

                emailService.send(subject, message, req.getClient().getLogin());

                // TODO change maybe
                if ("Admin".equals(user.getClass().getSimpleName())) {
                    session.setAttribute("requests", requestService.findAllRequests());
                } else if ("Master".equals(user.getClass().getSimpleName())) {
                    session.setAttribute("requests", requestService.findRequestsByMaster(user.getId()));
                } else {
                    session.removeAttribute("requests");
                }

                forward("requests");
            } else {
                throw new RequestFailException("Unable to create a new review");
            }
        } catch (NumberFormatException | NullPointerException e) {
            LOGGER.error(String.format("Invalid request id. %s", e.getMessage()), e);
            forward("unknown");
        } catch (RequestFailException e) {
            LOGGER.error(e.getMessage(), e);
            forward("unknown");
        }
    }
}
