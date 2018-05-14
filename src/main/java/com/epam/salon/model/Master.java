package com.epam.salon.model;

public class Master extends User {
    private String name;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Master(Long id, String login, String password) {
        this(id, login, password, null, null);
    }

    public Master(String login, String password) {
        this(null, login, password, null, null);
    }

    public Master(Long id, String login, String password, String name, String description) {
        super(id, login, password);
        this.description = description;
        this.name = name;
    }

    public Master(String login, String password, String name, String description) {
        this(null, login, password, name, description);
    }

    @Override
    public String toString() {
        return "Master{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
