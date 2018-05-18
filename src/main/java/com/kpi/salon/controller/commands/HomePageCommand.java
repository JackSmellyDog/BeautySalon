package com.kpi.salon.controller.commands;

import javax.servlet.ServletException;
import java.io.IOException;

public class HomePageCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        forward("home");
    }
}
