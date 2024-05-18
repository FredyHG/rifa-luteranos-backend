package dev.fredyhg.rifaluteranosbackend.models;

import dev.fredyhg.rifaluteranosbackend.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Table(name = "tb_admin")
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
}
