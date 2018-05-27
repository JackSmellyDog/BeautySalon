package com.kpi.salon.services.impl;

import com.kpi.salon.services.IEmailService;
import com.kpi.salon.utils.ResourcesManager;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;


public class EmailService implements IEmailService {
    private static final Logger LOGGER = Logger.getLogger(EmailService.class);

    private String user;
    private String password;

    private String host;
    private int port;
    private boolean sslFlag;

    public EmailService() {
        try {
            ResourcesManager resourcesManager = new ResourcesManager();
            Properties properties = resourcesManager.getProperties("email");

            user = properties.getProperty("user");
            password = properties.getProperty("password");

            host = properties.getProperty("host");
            port = Integer.parseInt(properties.getProperty("port"));
            sslFlag = Boolean.parseBoolean(properties.getProperty("ssl.flag"));

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void send(String subject, String message, String to) {
        try {
            Email email = new SimpleEmail();
            email.setHostName(host);
            email.setSmtpPort(port);
            email.setAuthenticator(new DefaultAuthenticator(user, password));
            email.setSSLOnConnect(sslFlag);

            email.setFrom(user);
            email.setSubject(subject);
            email.setMsg(message);
            email.addTo(to);
            email.send();

        } catch(Exception e){
            LOGGER.error(String.format("Unable to send email %s", to), e);
        }
    }
}
