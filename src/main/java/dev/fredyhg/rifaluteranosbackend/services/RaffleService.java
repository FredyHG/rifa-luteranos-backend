package dev.fredyhg.rifaluteranosbackend.services;

import dev.fredyhg.rifaluteranosbackend.dto.image.ImageResponse;
import dev.fredyhg.rifaluteranosbackend.dto.image.RafflePostRequest;
import dev.fredyhg.rifaluteranosbackend.exceptions.raffle.RaffleAlreadyExists;
import dev.fredyhg.rifaluteranosbackend.mappers.RaffleMapper;
import dev.fredyhg.rifaluteranosbackend.models.Raffle;
import dev.fredyhg.rifaluteranosbackend.repositories.RaffleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RaffleService {

    private final RaffleRepository raffleRepository;
    private final ImageService imageService;

    public void createRaffle(RafflePostRequest rafflePostRequest){

        Optional<Raffle> raffle = raffleRepository.findByTitle(rafflePostRequest.getTitle());

        raffle.ifPresent(raf -> {
            throw new RaffleAlreadyExists("Raffle already exists with title: " + rafflePostRequest.getTitle());
        });

        Raffle raffleToBeSaved = RaffleMapper.rafflePostRequestToRaffle(rafflePostRequest);

        ImageResponse imageResponse = imageService.uploadImage(rafflePostRequest.getImageBase64(), rafflePostRequest.getTitle());

        raffleToBeSaved.setImageUrl(imageResponse.getData().getLink());

        raffleRepository.save(raffleToBeSaved);
    }
}
