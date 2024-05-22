package dev.fredyhg.rifaluteranosbackend.services;

import dev.fredyhg.rifaluteranosbackend.dto.image.ImageResponse;
import dev.fredyhg.rifaluteranosbackend.services.feign.ImageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

    private static final String TYPE = "base64";

    private final ImageClient imageClient;

    @Value("${app.imgur.secret.clientId}")
    private String clientId;

    public ImageResponse uploadImage(String imageBase64, String title) {
        return imageClient.uploadImage("Client-ID " + clientId, TYPE, imageBase64, title);
    }

}
