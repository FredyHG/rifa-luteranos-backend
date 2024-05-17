package dev.fredyhg.rifaluteranosbackend.models;

import dev.fredyhg.rifaluteranosbackend.enums.StatusRaffle;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Raffle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private Double price;

    @Enumerated(EnumType.STRING)
    private StatusRaffle status;

    private boolean sold;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    private LocalDateTime createAt;

    @PrePersist
    private void onCreate(){
        createAt = LocalDateTime.now();
    }
}
