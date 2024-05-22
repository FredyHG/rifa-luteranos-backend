package dev.fredyhg.rifaluteranosbackend.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ResponseMessage {

    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
