package dev.fredyhg.rifaluteranosbackend.controllers;

import dev.fredyhg.rifaluteranosbackend.dto.ResponseMessage;
import dev.fredyhg.rifaluteranosbackend.dto.image.RafflePostRequest;
import dev.fredyhg.rifaluteranosbackend.services.RaffleService;
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

    private final RaffleService raffleService;

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createRaffle(@RequestBody RafflePostRequest rafflePostRequest){

        raffleService.createRaffle(rafflePostRequest);

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

}
