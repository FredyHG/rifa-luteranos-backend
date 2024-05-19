package dev.fredyhg.rifaluteranosbackend.services;

import dev.fredyhg.rifaluteranosbackend.dto.image.ImagePostRequest;
import dev.fredyhg.rifaluteranosbackend.repositories.RaffleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class RaffleService {

    private final RaffleRepository raffleRepository;
    private final ImageService imageService;

}
