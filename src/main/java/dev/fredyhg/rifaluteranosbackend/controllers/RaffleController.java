package dev.fredyhg.rifaluteranosbackend.controllers;

import dev.fredyhg.rifaluteranosbackend.dto.BuyRaffleRequest;
import dev.fredyhg.rifaluteranosbackend.dto.BuyRaffleResponse;
import dev.fredyhg.rifaluteranosbackend.dto.ResponseMessage;
import dev.fredyhg.rifaluteranosbackend.dto.image.RafflePostRequest;
import dev.fredyhg.rifaluteranosbackend.dto.mercadopago.MercadoPagoResponse;
import dev.fredyhg.rifaluteranosbackend.services.impl.MercadoPagoServiceImpl;
import dev.fredyhg.rifaluteranosbackend.services.impl.RaffleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("api/raffle")
@RequiredArgsConstructor
public class RaffleController {

    private final RaffleServiceImpl raffleServiceImpl;
    private final MercadoPagoServiceImpl mercadoPagoServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createRaffle(@RequestBody RafflePostRequest rafflePostRequest){

        raffleServiceImpl.createRaffle(rafflePostRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseMessage
                        .builder()
                        .statusCode(201)
                        .message("Created")
                        .description("Created raffle successfully")
                        .timestamp(new Date())
                        .build()
        );
    }


    @PostMapping("/callback")
    public ResponseEntity<ResponseMessage> mercadoPagoApiCallBack(@RequestBody MercadoPagoResponse mercadoPagoResponse) {

        mercadoPagoServiceImpl.checkPayment(mercadoPagoResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseMessage
                        .builder()
                        .statusCode(200)
                        .message("OK")
                        .description("OK")
                        .timestamp(new Date())
                        .build()
        );
    }

    @PostMapping("/buyRaffle")
    public ResponseEntity<BuyRaffleResponse> buyRaffle(@RequestBody BuyRaffleRequest buyRaffleRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(raffleServiceImpl.buyRaffle(buyRaffleRequest));
    }

}
