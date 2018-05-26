package com.kpi.salon.controller;

import com.kpi.salon.controller.commands.FrontCommand;
import com.kpi.salon.controller.commands.UnknownCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app")
public class FrontControllerServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(FrontControllerServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        handler(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        handler(req, resp);
    }


    private FrontCommand getCommand(HttpServletRequest request) {
        try {
            Class type = Class.forName(String.format(
                    "com.kpi.salon.controller.commands.%sCommand",
                    request.getParameter("command")
            ));

            return (FrontCommand) type.asSubclass(FrontCommand.class).newInstance();

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new UnknownCommand();
        }
    }

    private void handler(HttpServletRequest req, HttpServletResponse resp) {
        try {
            FrontCommand command = getCommand(req);
            command.init(getServletContext(), req, resp);
            command.process();
        } catch (IOException | ServletException e) {
            LOGGER.error(String.format("IO or Servlet exception happened: %s", e.getMessage()), e);
        } catch (Exception e) {
            LOGGER.error(String.format("Something unexpected happened: %s", e.getMessage()), e);
        }
    }
}
