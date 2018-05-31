package com.kpi.salon.controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();

        session.removeAttribute("user");
        session.removeAttribute("role");
        session.removeAttribute("masters");
        session.removeAttribute("requests");
        session.removeAttribute("reviews");
        session.removeAttribute("lastCommand");

        forward("login");
    }
}
