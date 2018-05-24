package com.kpi.salon.model;

import java.util.Objects;

public class Review {
    private Long id;
    private String text;
    private Integer rating;
    private Request request;

    public Review(Long id, String text, Integer rating, Request request) {
        this.id = id;
        this.text = text;
        this.rating = rating;
        this.request = request;
    }

    public Review(String text, Integer rating, Request request) {
        this(null, text, rating, request);
    }

    public Review(Long id, String text, Integer rating) {
        this(id, text, rating, null);
    }

    public Review() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(getId(), review.getId()) &&
                Objects.equals(getText(), review.getText()) &&
                Objects.equals(getRating(), review.getRating()) &&
                Objects.equals(getRequest(), review.getRequest());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getText(), getRating(), getRequest());
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                '}';
    }
}
