package com.kpi.salon.controller.commands;

import com.kpi.salon.model.Admin;
import com.kpi.salon.model.User;
import com.kpi.salon.services.impl.SmsService;
import com.kpi.salon.services.impl.UserService;
import com.kpi.salon.services.impl.ValidationService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand extends FrontCommand {
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            try {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                HttpSession session = request.getSession();

                UserService userService = new UserService();
                User user = userService.login(username, password);

                String role = user.getClass().getSimpleName();

                // TODO uncomment this method
                if ("Admin".equals(role)) {
                    Admin admin = (Admin) user;

//                ValidationService validationService = new ValidationService();
//                String validationCode = validationService.validationCode();
//
//                session.setAttribute("validationCode", validationCode);
                    session.setAttribute("unconfirmedUser", user);
//
//                SmsService smsService = new SmsService();
//                smsService.sendSms(validationCode, admin.getPhone());

                    forward("code");

                } else {

                    session.setAttribute("user", user);
                    session.setAttribute("role", role);

                    LOGGER.info(String.format("User %s has logged in", user.getLogin()));
                    forward("home");
                }
            } catch (Exception e) {
                LOGGER.warn(e.getMessage(), e);

                request.setAttribute("message", "Invalid data");
                forward("login");
            }
        } else {
            forward("login");
        }
    }
}
