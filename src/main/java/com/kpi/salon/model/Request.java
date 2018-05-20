package com.kpi.salon.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Request {
    private Long id;
    private LocalDateTime date;
    private Client client;
    private Master master;

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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public Request(Long id, LocalDateTime date, Client client, Master master) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.master = master;
    }

    public Request() {

    }
    public Request(LocalDateTime date, Client client, Master master) {
        this(null, date, client, master);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(getId(), request.getId()) &&
                Objects.equals(getDate(), request.getDate()) &&
                Objects.equals(getClient(), request.getClient()) &&
                Objects.equals(getMaster(), request.getMaster());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate(), getClient(), getMaster());
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", date=" + date +
                ", client=" + client +
                ", master=" + master +
                '}';
    }
}
