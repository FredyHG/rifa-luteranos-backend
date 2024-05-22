package dev.fredyhg.rifaluteranosbackend.exceptions.raffle;

import dev.fredyhg.rifaluteranosbackend.dto.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RaffleExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RaffleExceptionHandler.class);

    private static final Map<String, HttpStatus> statusTable = new HashMap<>();

    static {
        statusTable.put("RaffleException", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RaffleException.class)
    public ResponseEntity<ResponseMessage> handleRaffleException(RaffleException ex){
        logger.error("Exception handled: {}", ex.getMessage(), ex);

        HttpStatus status = mapStatus(ex);

        ResponseMessage responseMessage = ResponseMessage.builder()
                .statusCode(status.value())
                .message(status.name())
                .description(ex.getMessage())
                .timestamp(new Date())
                .build();

        return new ResponseEntity<>(responseMessage, status);
    }

    private HttpStatus mapStatus(RaffleException ex) {
        return statusTable.getOrDefault(ex.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    static {

        // HTTP STATUS 404
        statusTable.put(RaffleNotFound.class.getSimpleName(), HttpStatus.NOT_FOUND);

        // HTTP STATUS 409
        statusTable.put(RaffleAlreadyExists.class.getSimpleName(), HttpStatus.CONFLICT);
        statusTable.put(RaffleAlreadySold.class.getSimpleName(), HttpStatus.CONFLICT);
        statusTable.put(RaffleInvalidStatus.class.getSimpleName(), HttpStatus.CONFLICT);
    }
}
