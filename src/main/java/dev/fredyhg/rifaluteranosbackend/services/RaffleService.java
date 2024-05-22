package dev.fredyhg.rifaluteranosbackend.services;

import com.mercadopago.resources.payment.Payment;
import dev.fredyhg.rifaluteranosbackend.dto.BuyRaffleRequest;
import dev.fredyhg.rifaluteranosbackend.dto.BuyRaffleResponse;
import dev.fredyhg.rifaluteranosbackend.dto.buyer.BuyerDTO;
import dev.fredyhg.rifaluteranosbackend.dto.image.ImageResponse;
import dev.fredyhg.rifaluteranosbackend.dto.image.RafflePostRequest;
import dev.fredyhg.rifaluteranosbackend.exceptions.raffle.RaffleAlreadyExists;
import dev.fredyhg.rifaluteranosbackend.mappers.BuyerMapper;
import dev.fredyhg.rifaluteranosbackend.mappers.RaffleMapper;
import dev.fredyhg.rifaluteranosbackend.models.Buyer;
import dev.fredyhg.rifaluteranosbackend.models.Raffle;
import dev.fredyhg.rifaluteranosbackend.repositories.BuyerRepository;
import dev.fredyhg.rifaluteranosbackend.repositories.RaffleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RaffleService {

    private final ImageService imageService;
    private final MercadoPagoService mercadoPagoService;
    private final RaffleRepository raffleRepository;
    private final BuyerRepository buyerRepository;

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

    public Raffle findById(UUID uuid) {
        return raffleRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Raffle not found"));
    }

    public BuyRaffleResponse buyRaffle(BuyRaffleRequest buyRaffleRequest) {

        Raffle raffle = findById(UUID.fromString(buyRaffleRequest.getIdRaffle()));

        Buyer buyer = buyerRepository.findByCpf(buyRaffleRequest.getBuyer().getCpf())
                .orElseGet(() -> createBuyerIfNotExist(buyRaffleRequest.getBuyer()));

        Payment pix = mercadoPagoService.createPix(raffle, buyer);

        return BuyRaffleResponse
                .builder()
                .raffleTitle(raffle.getTitle())
                .buyerName(buyer.getName())
                .qrCodeBase64(pix.getPointOfInteraction().getTransactionData().getQrCodeBase64())
                .pixPaymentLink(pix.getPointOfInteraction().getTransactionData().getQrCode())
                .build();
    }



    private Buyer createBuyerIfNotExist(BuyerDTO buyerDTO){
        Buyer buyerToBeSaved = BuyerMapper.buyerDtoToBuyer(buyerDTO);

        return buyerRepository.save(buyerToBeSaved);
    }

}
