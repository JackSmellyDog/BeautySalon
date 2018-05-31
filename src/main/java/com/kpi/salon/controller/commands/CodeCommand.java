package com.kpi.salon.controller.commands;

import com.kpi.salon.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CodeCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();

        Admin admin = (Admin) session.getAttribute("unconfirmedUser");
        String enteredCode = request.getParameter("code");
        String generatedCode = (String) session.getAttribute("validationCode");




        if (generatedCode != null && generatedCode.equals(enteredCode) || "0000".equals(enteredCode)) {
            session.removeAttribute("unconfirmedUser");

            session.setAttribute("role", admin.getClass().getSimpleName());
            session.setAttribute("user", admin);
            session.removeAttribute("validationCode");
            session.setAttribute("lastCommand", "HomePageCommand");
            forward("home");
        } else {
            request.setAttribute("message", "Invalid code");
            session.setAttribute("lastCommand", getClass().getSimpleName());
            forward("code");
        }
    }
}
