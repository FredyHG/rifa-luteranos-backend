package dev.fredyhg.rifaluteranosbackend.mappers;

import dev.fredyhg.rifaluteranosbackend.dto.image.RafflePostRequest;
import dev.fredyhg.rifaluteranosbackend.models.Raffle;
import org.springframework.stereotype.Component;

@Component
public class RaffleMapper {

    private RaffleMapper(){
    }

    public static Raffle rafflePostRequestToRaffle(RafflePostRequest rafflePostRequest) {
            return Raffle
                    .builder()
                    .title(rafflePostRequest.getTitle())
                    .price(rafflePostRequest.getPrice())
                    .build();
    }

}
