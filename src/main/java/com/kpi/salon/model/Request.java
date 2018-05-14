package com.kpi.salon.model;

import java.time.LocalDateTime;

public class Request {
    private Long id;
    private LocalDateTime date;
    private Long clientId;
    private Long masterId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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

    public Request(Long id, LocalDateTime date, Long clientId, Long masterId) {
        this.id = id;
        this.date = date;
        this.clientId = clientId;
        this.masterId = masterId;
    }

    public Request(LocalDateTime date, Long clientId, Long masterId) {
        this(null, date, clientId, masterId);
    }
}
