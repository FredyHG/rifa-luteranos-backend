package dev.fredyhg.rifaluteranosbackend.services;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.resources.payment.Payment;
import dev.fredyhg.rifaluteranosbackend.dto.mercadopago.MercadoPagoResponse;
import dev.fredyhg.rifaluteranosbackend.dto.mercadopago.PaymentGetResponse;
import dev.fredyhg.rifaluteranosbackend.enums.StatusRaffle;
import dev.fredyhg.rifaluteranosbackend.exceptions.raffle.RaffleAlreadySold;
import dev.fredyhg.rifaluteranosbackend.exceptions.raffle.RaffleInvalidStatus;
import dev.fredyhg.rifaluteranosbackend.exceptions.raffle.RaffleNotFound;
import dev.fredyhg.rifaluteranosbackend.models.Buyer;
import dev.fredyhg.rifaluteranosbackend.models.Raffle;
import dev.fredyhg.rifaluteranosbackend.repositories.RaffleRepository;
import dev.fredyhg.rifaluteranosbackend.services.feign.MercadoPagoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MercadoPagoService {

    private final MercadoPagoClient mercadoPagoClient;

    private final RaffleRepository raffleRepository;

    @Value("${app.mercadopago.secret.apiKey}")
    private String apiKey;

    public void checkPayment(MercadoPagoResponse mercadoPagoResponse) {

        PaymentGetResponse paymentGetResponse =
                mercadoPagoClient.checkPaymentStatus(
                        mercadoPagoResponse.getData().getId(),
                        "Bearer " + apiKey);

        Raffle raffle =
                raffleRepository
                        .findById(
                                UUID.fromString(paymentGetResponse.getExternalReference())
                        ).orElseThrow(() -> new RaffleNotFound("Raffle not found"));


        if(paymentGetResponse.getStatus().equals("pending")) {
            raffle.setStatus(StatusRaffle.PENDING);
            raffleRepository.save(raffle);
        }

        if(paymentGetResponse.getStatus().equals("approved")){

            if (raffle.isSold()){
                throw new RaffleAlreadySold("Raffle has already been sold");
            }

            toggleSold(raffle);
        }

        if(paymentGetResponse.getStatus().equals("rejected") || paymentGetResponse.getStatus().equals("cancelled")) {
            System.out.println(paymentGetResponse.getStatus());
            resetBuyerRaffle(raffle);
        }

    }

    public Payment createPix(Raffle raffle, Buyer buyer) {
        MercadoPagoConfig.setAccessToken(apiKey);

        Map<String, String> customHeaders = new HashMap<>();
        customHeaders.put("x-idempotency-key", UUID.randomUUID().toString());

        MPRequestOptions requestOptions = MPRequestOptions.builder()
                .customHeaders(customHeaders)
                .build();

        PaymentClient client = new PaymentClient();

        OffsetDateTime now = OffsetDateTime.now();
        System.out.println(now);

        PaymentCreateRequest paymentCreateRequest =
                PaymentCreateRequest.builder()
                        .transactionAmount(BigDecimal.valueOf(raffle.getPrice()))
                        .externalReference(raffle.getId().toString())
                        .description(raffle.getTitle())
                        .paymentMethodId("pix")
                        .dateOfExpiration(now.plusMinutes(10))
                        .payer(
                                PaymentPayerRequest.builder()
                                        .email(buyer.getEmail())
                                        .firstName(buyer.getName())
                                        .identification(
                                                IdentificationRequest
                                                        .builder()
                                                        .type("CPF")
                                                        .number(buyer.getCpf())
                                                        .build())
                                        .build())
                        .build();

        try {
            return client.create(paymentCreateRequest, requestOptions);
        } catch (Exception ex) {
            throw new RuntimeException("Error to create order");
        }
    }

    public void toggleSold(Raffle raffle) {

        raffle.setStatus(StatusRaffle.SOLD);
        raffle.setSold(true);

        raffleRepository.save(raffle);
    }

    private void resetBuyerRaffle(Raffle raffle) {

        System.out.println("Cancelando Rifa");

        raffle.setStatus(StatusRaffle.WAIT_BUYER);
        raffle.setBuyer(null);

        raffleRepository.save(raffle);

        System.out.println("Rifa resetada");
    }

}
