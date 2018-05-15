package com.kpi.salon.services.impl;

import com.kpi.salon.services.IEmailService;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class EmailService implements IEmailService {
    private static final Logger LOGGER = Logger.getLogger(EmailService.class);

    private String user;
    private String password;

    private String host;
    private int port;
    private boolean sslFlag;

    public EmailService() {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader(loadResources()));

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
            LOGGER.error("Unable to send email", e);
        }
    }

    private File loadResources() {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource("email.properties").getFile());
    }
}
