package com.kpi.salon.tags;

import com.kpi.salon.model.Request;
import com.kpi.salon.model.Status;
import com.kpi.salon.services.impl.RequestService;
import com.kpi.salon.services.impl.UserService;
import org.apache.log4j.Logger;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleTag extends SimpleTagSupport {
    private static final Logger LOGGER = Logger.getLogger(ScheduleTag.class);
    private String masterId;
    private String clientId;

    private UserService userService = new UserService();
    private RequestService requestService = new RequestService();

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }


    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        StringBuilder builder = new StringBuilder();

        Long mId = Long.parseLong(masterId);

        List<Request> requests = requestService.findRequestsByMaster(mId);
        LocalDateTime currentDate = LocalDateTime.now().withHour(0).withMinute(0);


        requests = requests.stream()
                .filter(r -> r.getStatus() != Status.CANCELED)
                .filter(r -> r.getDate().isAfter(currentDate) && r.getDate().isBefore(currentDate.plusWeeks(1)))
                .collect(Collectors.toList());


        StringBuilder tbody = new StringBuilder();
        tbody.append("<tbody>");

        int today = currentDate.getDayOfWeek().getValue();
        Long clientId = Long.parseLong(this.clientId);

        for (int i = 9; i < 18; i++) {
            tbody.append("<tr>");
            tbody.append("<td>").append(i).append(":00").append("</td>");


            for (int j = today ; j < today + 7; j++) {
                tbody.append("<td style='width: 100px;'");
                int correctDay = (j % 7) == 0? 7 : (j % 7);
                for (Request req : requests) {
                    if (req.getDate().getDayOfWeek().getValue() == correctDay && req.getDate().getHour() == i) {
                        if (req.getClient().getId().longValue() == clientId) {
                            tbody.append(" class='bg-success'");
                        } else {
                            tbody.append(" class='bg-danger'");
                        }
                    }
                }
                tbody.append("></td>");
            }
            tbody.append("</tr>");
        }
        tbody.append("</tbody>");

        StringBuilder thead = new StringBuilder();
        thead.append("<thead>").append("<tr>")
                .append("<th style='width: 9%'>").append("Time").append("</th>");

        for (int i = 0; i < 7; i++) {
            LocalDateTime date = currentDate.plusDays(i);

            thead.append("<th style='width: 13%'>")
                    .append(date.getDayOfWeek().name()).append("<br>")
                    .append("<p class='h6'>")
                        .append(date.getDayOfMonth()).append('-')
                        .append(date.getMonth()).append('-')
                        .append(date.getYear())
                    .append("</p>")
                    .append("</th>");
        }

        thead.append("</tr>").append("</thead>");


        builder.append("<table class=\"table table-bordered\">")
                .append(thead)
                .append(tbody)
                .append("</table>");

        out.print(builder.toString());
    }
}
