package com.openclassrooms.chatop.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class MessageDto {

    @JsonProperty("rental_id")
    private Integer rentalId;

    @JsonProperty("user_id")
    private Integer userId;

    private String message;

    private Instant createdAt;

    private Instant updatedAt;


    public Integer getRentalId() {
        return rentalId;
    }

    public void setRentalId(Integer rentalId) {
        this.rentalId = rentalId;
    }

    public Integer getUserId() { return userId;}

    public void setUserId(Integer userId) { this.userId = userId; }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getCreatedAt() {
        if (this.createdAt == null) {
            return Instant.now();
        }
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        if (this.updatedAt == null) {
            return Instant.now();
        }
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
