package dev.fredyhg.rifaluteranosbackend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuyRaffleResponse {

    private String buyerName;
    private String raffleTitle;
    private String qrCodeBase64;
    private String pixPaymentLink;

}
