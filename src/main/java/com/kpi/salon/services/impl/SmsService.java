package com.kpi.salon.services.impl;

import com.google.gson.Gson;
import com.kpi.salon.services.ISmsService;
import com.kpi.salon.utils.ResourcesManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.UUID;

public class SmsService implements ISmsService {
    private static final Logger LOGGER = Logger.getLogger(SmsService.class);

    private static final String URL = "https://app.bsg.hk/rest/sms/create";
    private String apiKey;

    public SmsService() {
        try {
            ResourcesManager resourcesManager = new ResourcesManager();
            Properties properties = resourcesManager.getProperties("sms");
            apiKey = properties.getProperty("x.api.key");

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void sendSms(String text, String number) {
        Gson gson = new Gson();
        String json = gson.toJson(new SMS(text, number));

        try {
            Document document = Jsoup.connect(URL)
                    .header("Content-type", "application/json")
                    .header("X-API-KEY", apiKey)
                    .ignoreContentType(true)
                    .requestBody(json)
                    .post();

            LOGGER.info("Message has been sent");
            LOGGER.info(document.body().text());

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private class SMS {
        final String destination;
        final String originator;
        final String body;
        final String msisdn;
        final String reference;

        SMS(String body, String msisdn) {
            this.destination = "phone";
            this.originator = "info";
            this.body = body;
            this.msisdn = msisdn;
            this.reference = UUID.randomUUID().toString().substring(0, 13);
        }
    }
}
