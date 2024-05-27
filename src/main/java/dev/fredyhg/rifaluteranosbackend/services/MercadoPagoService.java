package dev.fredyhg.rifaluteranosbackend.services;

import com.mercadopago.resources.payment.Payment;
import dev.fredyhg.rifaluteranosbackend.dto.mercadopago.MercadoPagoResponse;
import dev.fredyhg.rifaluteranosbackend.models.Buyer;
import dev.fredyhg.rifaluteranosbackend.models.Raffle;

public interface MercadoPagoService {
    void checkPayment(MercadoPagoResponse mercadoPagoResponse);

    Payment createPix(Raffle raffle, Buyer buyer);
}
