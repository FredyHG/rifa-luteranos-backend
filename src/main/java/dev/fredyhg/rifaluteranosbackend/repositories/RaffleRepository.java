package dev.fredyhg.rifaluteranosbackend.repositories;

import dev.fredyhg.rifaluteranosbackend.models.Raffle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RaffleRepository extends JpaRepository<Raffle, UUID> {
}
