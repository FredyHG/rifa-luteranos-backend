package dev.fredyhg.rifaluteranosbackend.controllers;

import dev.fredyhg.rifaluteranosbackend.dto.image.ImagePostRequest;
import dev.fredyhg.rifaluteranosbackend.services.RaffleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/raffle")
@RequiredArgsConstructor
public class RaffleController {

    private final RaffleService raffleService;

}
