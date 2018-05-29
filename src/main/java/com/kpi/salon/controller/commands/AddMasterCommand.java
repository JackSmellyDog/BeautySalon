package com.kpi.salon.controller.commands;

import com.kpi.salon.exceptions.InvalidUserDataException;
import com.kpi.salon.exceptions.SuchUserIsExistException;
import com.kpi.salon.services.impl.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class AddMasterCommand extends FrontCommand {
    private static final Logger LOGGER = Logger.getLogger(AddMasterCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String name = request.getParameter("last_first_name");
            String description = request.getParameter("description");

            Part filePart = request.getPart("avatar");

            LocalDateTime localDateTime = LocalDateTime.now();

            String filePrefix = String.format("%s%s%s",
                    localDateTime.getSecond(),
                    localDateTime.getMinute(),
                    localDateTime.getDayOfMonth()
            );

            String filename = filePrefix + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            InputStream fileContent = filePart.getInputStream();

            byte[] buffer = new byte[fileContent.available()];
            fileContent.read(buffer);

            String directory = context.getInitParameter("uploadDirectory");

            File targetFile = new File(directory + filename);
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);

            UserService userService = new UserService();

            if (!userService.addMaster(username, password, confirmPassword, name, description, filename)) {
                throw new InvalidUserDataException();
            }
            request.getSession().setAttribute("masters", userService.findAllMasters());
            forward("masters");

        } catch (InvalidUserDataException | SuchUserIsExistException e) {
            LOGGER.error(e.getMessage(), e);
            request.setAttribute("error", "Invalid data or user is already exist");
            forward("masters");
        }

    }
}
