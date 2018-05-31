package com.kpi.salon.controller.commands;

import com.kpi.salon.model.Request;
import com.kpi.salon.model.Review;
import com.kpi.salon.services.impl.RequestService;
import com.kpi.salon.services.impl.ReviewService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddReviewCommand extends FrontCommand {
    private static final Logger LOGGER = Logger.getLogger(AddReviewCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();


        if ("POST".equalsIgnoreCase(request.getMethod())) {
            session.setAttribute("lastCommand", getClass().getSimpleName());
            ReviewService reviewService = new ReviewService();

            Request req = (Request) session.getAttribute("for_review");

            String text = request.getParameter("text");
            Integer rating = Integer.parseInt(request.getParameter("rating"));

            Review oldReview = reviewService.findReviewByRequest(req);


            if (reviewService.updateTextAndRating(oldReview.getId(), text, rating)) {

                session.setAttribute("reviews", reviewService.findAllReviews());
                session.setAttribute("lastCommand", "ReviewPageCommand");

                forward("reviews");
            }

        } else {
            RequestService requestService = new RequestService();
            Long id = Long.parseLong(request.getParameter("id"));
            Request req = requestService.findRequestById(id);

            session.setAttribute("for_review", req);
            session.setAttribute("lastCommand", getClass().getSimpleName());
            forward("newreview");
        }
    }
}
