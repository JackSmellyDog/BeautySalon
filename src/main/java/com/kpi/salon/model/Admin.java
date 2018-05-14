package com.kpi.salon.model;

public class Admin extends User {
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Admin(Long id, String login, String password, String phone) {
        super(id, login, password);
        this.phone = phone;
    }
}
