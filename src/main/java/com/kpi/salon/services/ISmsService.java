package com.kpi.salon.services;

public interface ISmsService {
    // int random_number1 = (int) (Math.random() * 9)
    void sendSms(String text, String number);
}
