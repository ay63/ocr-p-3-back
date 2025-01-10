package com.openclassrooms.chatop.entities;

import com.openclassrooms.chatop.constraints.multipart.NotEmptyMultipartFile;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "rentals")
public class Rental implements Timestampable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Positive
    @Column(name = "surface", precision = 6)
    private Double surface;

    @NotNull
    @Positive
    @Column(name = "price", precision = 6)
    private BigDecimal price;

    @NotEmptyMultipartFile
    @Size(max = 255)
    @Column(name = "picture")
    private String picture;

    @NotNull
    @Size(max = 2000)
    @Column(name = "description", length = 2000)
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(name = "created_at")
    @NotNull
    private Instant createdAt;

    @Column(name = "updated_at")
    @NotNull
    private Instant updatedAt;

}