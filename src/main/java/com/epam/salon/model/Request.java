package com.epam.salon.model;

public class Request {
    private Long id;
    private String date; // change to something else
    private Long clientId;
    private Long masterId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public Request(Long id, String date, Long clientId, Long masterId) {
        this.id = id;
        this.date = date;
        this.clientId = clientId;
        this.masterId = masterId;
    }

    public Request(String date, Long clientId, Long masterId) {
        this(null, date, clientId, masterId);
    }
}
