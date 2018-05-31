package com.kpi.salon.controller.commands;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SetLanguageCommand extends FrontCommand {
    private static final Logger LOGGER = Logger.getLogger(SetLanguageCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        try {
            //String from = request.getHeader("Referer");
            String lang = request.getParameter("lang");
            HttpSession session = request.getSession();

            if ("UA".equals(lang)) {
                session.setAttribute("lang", lang);
            } else {
                session.setAttribute("lang", "EN");
            }


                String lastCommand = (String ) session.getAttribute("lastCommand");

                switch (lastCommand) {
                    case "AddReviewCommand":
                        forward("newreview");
                        break;
                    case "AddRequestCommand":
                        forward("newrequest");
                        break;
                    case "RegisterCommand":
                        forward("registration");
                        break;
                    case "LoginCommand":
                        forward("login");
                        break;
                    case "RequestPageCommand":
                        forward("requests");
                        break;
                    case "MasterPageCommand":
                        forward("masters");
                        break;
                    case "ReviewPageCommand":
                        forward("reviews");
                        break;
                    case "CodeCommand":
                        forward("code");
                        break;

                    default:
                        forward("home");
                        break;
                }


        } catch (NullPointerException e) {
            LOGGER.error(e.getMessage(), e);
            forward("home");
        }
    }
}
