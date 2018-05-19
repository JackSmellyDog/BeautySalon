package com.kpi.salon.model;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(getId(), request.getId()) &&
                Objects.equals(getClientId(), request.getClientId()) &&
                Objects.equals(getMasterId(), request.getMasterId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getClientId(), getMasterId());
    }
}
