package com.kpi.salon.controller.commands;

import com.kpi.salon.model.Client;
import com.kpi.salon.model.Request;
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
        HttpSession session = request.getSession();

        Long id = Long.parseLong(request.getParameter("id"));
        Client client = (Client) session.getAttribute("user");

        if (id != null) {
            EmailService emailService = new EmailService();
            ReviewService reviewService = new ReviewService();
            RequestService requestService = new RequestService();

            Request req = requestService.findRequestById(id);

            if (reviewService.create("", 0, req)) {

                String subject = "Leave a review";
                String message = String.format("http://localhost:8080/app?command=AddReview&id=%d", id);

                emailService.send(subject, message, client.getLogin());

                forward("requests");
            } else {
                //TODO
            }

        } else {
            forward("unknown");
        }
    }
}
