package dev.fredyhg.rifaluteranosbackend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "tb_buyer")
@Entity
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private String number;

    @Column(name = "cpf")
    private String cpf;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<Raffle> raffles;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @PrePersist
    private void onCreate(){
        createAt = LocalDateTime.now();
    }
}
