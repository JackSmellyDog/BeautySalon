package com.epam.salon.controller;

import com.epam.salon.controller.commands.FrontCommand;
import com.epam.salon.controller.commands.UnknownCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app")
public class FrontControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handler(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handler(req, resp);
    }

    private FrontCommand getCommand(HttpServletRequest request) {
        try {
            Class type = Class.forName(String.format(
                    "com.epam.salon.controller.commands.%sCommand",
                    request.getParameter("command")
            ));

            return (FrontCommand) type.asSubclass(FrontCommand.class).newInstance();

        } catch (Exception e) {
            return new UnknownCommand();
        }
    }

    private void handler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FrontCommand command = getCommand(req);
        command.init(getServletContext(), req, resp);
        command.process();
    }
}
