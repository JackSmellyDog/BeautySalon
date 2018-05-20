package com.kpi.salon.controller.commands;

import javax.servlet.ServletException;
import java.io.IOException;

public class CompleteRequestCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println(id);
    }
}
