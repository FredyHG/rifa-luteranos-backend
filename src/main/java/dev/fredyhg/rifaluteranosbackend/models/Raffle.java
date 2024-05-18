package dev.fredyhg.rifaluteranosbackend.models;

import dev.fredyhg.rifaluteranosbackend.enums.StatusRaffle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(name = "tb_raffle")
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Raffle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusRaffle status;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "sold")
    private boolean sold;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @PrePersist
    private void onCreate(){
        sold = false;
        buyer = null;
        status = StatusRaffle.WAIT_BUYER;
        createAt = LocalDateTime.now();
    }
}
