package com.kpi.salon.services;

public interface IEmailService {
    void send(String subject, String message, String to);
}
