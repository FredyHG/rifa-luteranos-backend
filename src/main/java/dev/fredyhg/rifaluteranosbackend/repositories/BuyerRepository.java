package dev.fredyhg.rifaluteranosbackend.repositories;

import dev.fredyhg.rifaluteranosbackend.models.Buyer;
import dev.fredyhg.rifaluteranosbackend.models.Raffle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BuyerRepository extends JpaRepository<Buyer, UUID> {

    Optional<Buyer> findByCpf(String cpf);

}
