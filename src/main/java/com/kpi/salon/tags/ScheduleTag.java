package com.kpi.salon.tags;

import com.kpi.salon.model.Request;
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

    private UserService userService = new UserService();
    private RequestService requestService = new RequestService();

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        StringBuilder builder = new StringBuilder();

        Long mId = Long.parseLong(masterId);

        List<Request> requests = requestService.findRequestsByMaster(mId);
        LocalDateTime currentDate = LocalDateTime.now().withHour(0).withMinute(0);


        requests = requests.stream()
                .filter(r -> r.getDate().isAfter(currentDate) && r.getDate().isBefore(currentDate.plusWeeks(1)))
                .collect(Collectors.toList());


        // TODO work this crazy stuff out
        StringBuilder tbody = new StringBuilder();
        tbody.append("<tbody>");

        for (int i = 9; i < 18; i++) {
            tbody.append("<tr>");
            tbody.append("<td>").append(i).append(":00").append("</td>");
            int today = currentDate.getDayOfWeek().getValue();
            for (int j = today ; j < today + 7; j++) {
                tbody.append("<td style='width: 100px;'");

                for (Request req : requests) {
                    if (req.getDate().getDayOfWeek().getValue() == (j % 7) && req.getDate().getHour() == i) {
                        tbody.append(" class='bg-danger'");
                    }
                }

                tbody.append("></td>");
            }
            tbody.append("</tr>");
        }

        tbody.append("</tbody>");

        builder.append("<table class=\"table table-bordered\">")
                .append("<thead>").append("<tr>")

                .append("<th>").append("Time").append("</th>")
                .append("<th>").append(currentDate.getDayOfWeek().name()).append("</th>")
                .append("<th>").append(currentDate.plusDays(1).getDayOfWeek().name()).append("</th>")
                .append("<th>").append(currentDate.plusDays(2).getDayOfWeek().name()).append("</th>")
                .append("<th>").append(currentDate.plusDays(3).getDayOfWeek().name()).append("</th>")
                .append("<th>").append(currentDate.plusDays(4).getDayOfWeek().name()).append("</th>")
                .append("<th>").append(currentDate.plusDays(5).getDayOfWeek().name()).append("</th>")
                .append("<th>").append(currentDate.plusDays(6).getDayOfWeek().name()).append("</th>")
                .append("</tr>").append("</thead>")
                .append(tbody)
                .append("</table>");


        out.print(builder.toString());
    }
}
