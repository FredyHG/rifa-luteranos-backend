package dev.fredyhg.rifaluteranosbackend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String number;

    private String cpf;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<Raffle> raffles;

    private LocalDateTime createAt;

    @PrePersist
    private void onCreate(){
        createAt = LocalDateTime.now();
    }
}
