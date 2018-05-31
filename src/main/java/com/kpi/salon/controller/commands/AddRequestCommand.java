package com.kpi.salon.controller.commands;

import com.kpi.salon.exceptions.RequestFailException;
import com.kpi.salon.model.Request;
import com.kpi.salon.model.Status;
import com.kpi.salon.model.User;
import com.kpi.salon.services.impl.RequestService;
import com.kpi.salon.services.impl.UserService;
import com.kpi.salon.services.impl.ValidationService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class AddRequestCommand extends FrontCommand {
    private static final Logger LOGGER = Logger.getLogger(AddRequestCommand.class);

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserService userService = new UserService();

        if ("POST".equalsIgnoreCase(request.getMethod())){
            try {
                RequestService requestService = new RequestService();

                User user = (User) session.getAttribute("user");
                if (user == null)
                    throw new RequestFailException("Unauthorized user can't make requests");


                session.setAttribute("lastCommand", getClass().getSimpleName());
                Long clientId = user.getId();

                String masterId = request.getParameter("master_id");
                String date = request.getParameter("date");

                if (masterId == null || date == null)
                    throw new RequestFailException("Invalid date or master");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

                ValidationService validationService = new ValidationService();

                if (!validationService.isRequestDateValid(dateTime)) {
                    throw new RequestFailException("Unacceptable period of time or invalid data");
                }

                List<Request> requests = requestService.findRequestsByMaster(Long.parseLong(masterId));

                for (Request r : requests) {
                    if (r.getStatus() != Status.CANCELED && r.getDate().isEqual(dateTime)) {
                        throw new RequestFailException("This period of time is already booked");
                    }
                }


                if (requestService.create(dateTime, clientId, Long.parseLong(masterId))) {
                    session.setAttribute("requests", requestService.findRequestsByClient(user.getId()));
                    request.setAttribute("chosen_master_id", masterId);
                    forward("newrequest");
                } else {
                    forward("unknown");
                }

            } catch (NumberFormatException | DateTimeParseException | RequestFailException e) {
                LOGGER.error(e.getMessage(), e);
                request.setAttribute("error", "Invalid request data");
                session.setAttribute("lastCommand", getClass().getSimpleName());
                forward("newrequest");
            }

        } else {
            String masterId = request.getParameter("id");

            if (masterId != null) {
                request.setAttribute("chosen_master_id", masterId);
            }

            session.setAttribute("masters", userService.findAllMasters());
            session.setAttribute("lastCommand", getClass().getSimpleName());

            forward("newrequest");
        }
    }
}
