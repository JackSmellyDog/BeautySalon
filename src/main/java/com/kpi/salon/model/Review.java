package com.kpi.salon.model;

import java.util.Objects;

public class Review {
    private Long id;
    private String text;
    private Long requestId;

    public Review(Long id, String text, Long requestId) {
        this.id = id;
        this.text = text;
        this.requestId = requestId;
    }

    public Review(String text, Long requestId) {
        this(null, text, requestId);
    }


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

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(getId(), review.getId()) &&
                Objects.equals(getRequestId(), review.getRequestId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRequestId());
    }
}
