package com.kpi.salon.model;


public class Client extends User {
    public Client(Long id, String login, String password) {
        super(id, login, password);
    }

    public Client(String login, String password) {
        this(null, login, password);
    }
}
