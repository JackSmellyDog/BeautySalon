package com.epam.salon.controller.commands;

import com.epam.salon.model.Request;
import com.epam.salon.model.User;
import com.epam.salon.services.RequestService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

public class NewRequestCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        if ("POST".equalsIgnoreCase(request.getMethod())){

            response.setContentType("text/html");

            RequestService requestService = new RequestService();

            User user = (User) request.getSession().getAttribute("user");
            Long clientId = user.getId();

            String masterId = request.getParameter("master_id");

            String date = request.getParameter("date");
            String time = request.getParameter("time");

            PrintWriter writer = response.getWriter();

            StringBuilder builder = new StringBuilder();

            builder.append(clientId).append("<br>")
                    .append(masterId).append("<br>")
                    .append(date).append("<br>")
                    .append(time);


            writer.println(builder.toString());
            writer.close();

            //requestService.create(new Request());


        } else {
            forward("newrequest");
        }
    }
}
