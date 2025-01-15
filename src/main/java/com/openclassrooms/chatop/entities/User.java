package com.openclassrooms.chatop.entities;

import com.openclassrooms.chatop.constraints.password.ValidPassword;
import com.openclassrooms.chatop.entities.interfaces.Timestampable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails, Timestampable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Size(max = 255)
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Size(min = 2, max = 255)
    @Column(name = "name")
    private String name;

    @ValidPassword
    @Size(max = 255)
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "created_at")
    private Instant createdAt;

    @NotNull
    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Message> messages;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}