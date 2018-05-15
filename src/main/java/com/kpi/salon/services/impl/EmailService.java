package com.kpi.salon.services.impl;

import com.kpi.salon.services.IEmailService;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;



public class EmailService implements IEmailService {
    private static final Logger LOGGER = Logger.getLogger(EmailService.class);
    private static final String HOST = "smtp.gmail.com";
    private static final int PORT = 465;
    private static final boolean SSL_FLAG = true;


    @Override
    public boolean send() {
        String userName = "************";

        String password = "*************";

        String fromAddress="**********";

        String toAddress =  "****************";

        String subject = "Test Mail";

        String message = "Hello from Apache Mail";



        try {

            Email email = new SimpleEmail();

            email.setHostName(HOST);

            email.setSmtpPort(PORT);

            email.setAuthenticator(new DefaultAuthenticator(userName, password));

            email.setSSLOnConnect(SSL_FLAG);

            email.setFrom(fromAddress);

            email.setSubject(subject);

            email.setMsg(message);

            email.addTo(toAddress);

            email.send();

            return true;

        }catch(Exception ex){
            System.out.println("Unable to send email");

            System.out.println(ex);
            return false;
        }

    }
}
