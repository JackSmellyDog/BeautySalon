package com.kpi.salon.controller.commands;


import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

public class TestCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(TestCommand.class);
    @Override
    public void process() throws ServletException, IOException {

            HttpSession session = request.getSession();

            LOGGER.info(request.getPathInfo());
            LOGGER.info(request.getQueryString());
            LOGGER.info(request.getRequestURI());
            LOGGER.info(request.getContextPath());
            LOGGER.info(request.getServletPath());
            LOGGER.info(request.getHeader("Referer"));




            forward("test");

    }
}
