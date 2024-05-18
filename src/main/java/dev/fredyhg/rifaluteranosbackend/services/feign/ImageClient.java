package dev.fredyhg.rifaluteranosbackend.services.feign;

import dev.fredyhg.rifaluteranosbackend.dto.image.ImageResponse;
import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;

@FeignClient(name = "imageClient", url = "https://api.imgur.com")
public interface ImageClient {

    @PostMapping(value = "/3/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Headers("Authorization: Client-ID {clientId}")
    ImageResponse uploadImage(@Param("clientId") String clientId,
                              @RequestPart("type") String type,
                              @RequestPart("image") String image,
                              @RequestPart("title") String title);

}
