package dev.fredyhg.rifaluteranosbackend.dto.buyer;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BuyerDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String number;

    @NotBlank
    private String cpf;

    @NotBlank
    private String email;
}
