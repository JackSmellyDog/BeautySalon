package com.kpi.salon.controller.commands;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import java.io.IOException;

public class UnknownCommand extends FrontCommand {
    private static final Logger LOGGER = Logger.getLogger(UnknownCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        forward("unknown");
    }
}
