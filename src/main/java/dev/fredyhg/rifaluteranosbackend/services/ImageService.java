package dev.fredyhg.rifaluteranosbackend.services;

import dev.fredyhg.rifaluteranosbackend.dto.image.ImageResponse;

public interface ImageService {

    ImageResponse uploadImage(String imageBase64, String title);
}
