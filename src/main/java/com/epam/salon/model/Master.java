package com.epam.salon.model;

public class Master extends User {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Master(String login, String password) {
        this(login, password, null);
    }

    public Master(String login, String password, String description) {
        super(login, password);
        this.description = description;
    }
}
