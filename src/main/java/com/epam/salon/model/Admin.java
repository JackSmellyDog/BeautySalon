package com.epam.salon.model;

public class Admin extends User {
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Admin(String login, String password, String phone) {
        super(login, password);
        this.phone = phone;
    }
}
