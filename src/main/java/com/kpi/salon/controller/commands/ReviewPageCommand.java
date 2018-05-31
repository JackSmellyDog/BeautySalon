package com.kpi.salon.controller.commands;

import com.kpi.salon.services.impl.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ReviewPageCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if ("Admin".equals(role)) {
            ReviewService reviewService = new ReviewService();
            session.setAttribute("reviews", reviewService.findAllReviews());

            String page = request.getParameter("page");
            request.setAttribute("page", (page == null)? 1 : page);
            session.setAttribute("lastCommand", getClass().getSimpleName());

            forward("reviews");
        } else {
            forward("403");
        }
    }
}
