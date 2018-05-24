package com.kpi.salon.controller.commands;

import com.kpi.salon.services.impl.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ReviewPageCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();
        ReviewService reviewService = new ReviewService();

        if (session.getAttribute("reviews") == null) {
            session.setAttribute("reviews", reviewService.findAllReviews());
        }

        forward("reviews");
    }
}
