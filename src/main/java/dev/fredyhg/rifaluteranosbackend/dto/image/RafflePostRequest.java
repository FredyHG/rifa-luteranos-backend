package dev.fredyhg.rifaluteranosbackend.dto.image;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RafflePostRequest {

    @NotBlank(message = "the title was not informed")
    private String title;

    private Double price;

    private String imageBase64;
}
