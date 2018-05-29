package com.kpi.salon.controller.commands;


import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

public class TestCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(TestCommand.class);
    @Override
    public void process() throws ServletException, IOException {
        if ("POST".equalsIgnoreCase(request.getMethod())) {

            Part filePart = request.getPart("avatar"); // Retrieves <input type="file" name="file">
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            InputStream fileContent = filePart.getInputStream();

            byte[] buffer = new byte[fileContent.available()];
            fileContent.read(buffer);

            String directory = context.getInitParameter("uploadDirectory");

            LOGGER.info(directory);

            File targetFile = new File(directory + "/test.png");
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);

            LOGGER.info(fileName);


        } else {
            forward("test");
        }
    }
}
