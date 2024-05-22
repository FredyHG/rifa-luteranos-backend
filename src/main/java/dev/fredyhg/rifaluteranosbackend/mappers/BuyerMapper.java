package dev.fredyhg.rifaluteranosbackend.mappers;

import dev.fredyhg.rifaluteranosbackend.dto.buyer.BuyerDTO;
import dev.fredyhg.rifaluteranosbackend.models.Buyer;
import org.springframework.stereotype.Component;

@Component
public class BuyerMapper {

    public static Buyer buyerDtoToBuyer(BuyerDTO buyerDTO){
        return Buyer
                .builder()
                .name(buyerDTO.getName())
                .cpf(buyerDTO.getCpf())
                .email(buyerDTO.getEmail())
                .number(buyerDTO.getNumber())
                .build();
    }

}
