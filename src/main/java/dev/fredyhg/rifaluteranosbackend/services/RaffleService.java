package dev.fredyhg.rifaluteranosbackend.services;

import dev.fredyhg.rifaluteranosbackend.dto.BuyRaffleRequest;
import dev.fredyhg.rifaluteranosbackend.dto.BuyRaffleResponse;
import dev.fredyhg.rifaluteranosbackend.dto.image.RafflePostRequest;
import dev.fredyhg.rifaluteranosbackend.models.Raffle;

import java.util.UUID;

public interface RaffleService {
    BuyRaffleResponse buyRaffle(BuyRaffleRequest buyRaffleRequest);

    Raffle findById(UUID uuid);

    void createRaffle(RafflePostRequest rafflePostRequest);
}
