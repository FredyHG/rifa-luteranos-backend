package dev.fredyhg.rifaluteranosbackend.dto;

import dev.fredyhg.rifaluteranosbackend.dto.buyer.BuyerDTO;
import lombok.Data;

@Data
public class BuyRaffleRequest {

    private String idRaffle;
    private BuyerDTO buyer;
}
