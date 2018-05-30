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
            String from = request.getHeader("Referer");
            String lang = request.getParameter("lang");
            HttpSession session = request.getSession();

            if ("UA".equals(lang)) {
                session.setAttribute("lang", lang);
            } else {
                session.setAttribute("lang", "EN");
            }


            if (from != null) {
                URI uri = new URI(from);
                String query = uri.getQuery();

                String command = query.split("&")[0].split("=")[1];

                switch (command) {
                    case "MasterPage":
                        forward("masters");
                        break;
                    case "ReviewPage":
                        forward("reviews");
                        break;
                    case "DeleteMaster":
                        forward("masters");
                        break;
                    case "Code":
                        forward("code");
                        break;
                    case "CompleteRequest":
                        forward("requests");
                        break;
                    case "CancelRequest":
                        forward("requests");
                        break;
                    case "AddReview":
                        forward("newreview");
                        break;
                    case "AddRequest":
                        forward("newrequest");
                        break;
                    case "AddMaster":
                        forward("masters");
                        break;
                    default:
                        forward("home");
                        break;
                }

                LOGGER.info(command);


            } else {
                forward("home");
            }

        } catch (NullPointerException | URISyntaxException e) {
            LOGGER.error(e.getMessage(), e);
            forward("home");
        }

    }
}
