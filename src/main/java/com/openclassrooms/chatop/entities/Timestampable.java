package com.openclassrooms.chatop.entities;

import java.time.Instant;

public interface Timestampable {

    Instant getCreatedAt();
    void setCreatedAt(Instant createdAt);

    Instant getUpdatedAt();
    void setUpdatedAt(Instant updatedAt);

}
