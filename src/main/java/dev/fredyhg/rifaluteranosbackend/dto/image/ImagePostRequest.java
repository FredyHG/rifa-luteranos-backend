package dev.fredyhg.rifaluteranosbackend.dto.image;

import lombok.Data;

@Data
public class ImagePostRequest {
    private String imageBase64;
    private String title;
}
