package com.kpi.salon.controller.commands;

import com.kpi.salon.model.Admin;
import com.kpi.salon.services.IEmailService;
import com.kpi.salon.services.impl.EmailService;
import com.kpi.salon.services.impl.UserService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TestCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        EmailService emailService = new EmailService();
        emailService.send();
    }
}
