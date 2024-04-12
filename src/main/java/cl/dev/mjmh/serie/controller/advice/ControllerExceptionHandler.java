package cl.dev.mjmh.serie.controller.advice;

import cl.dev.mjmh.serie.exception.InternalServerException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    @ExceptionHandler(value = {InternalServerException.class, RuntimeException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage internalServerError(RuntimeException ex, WebRequest request) {
        LOGGER.error("Error interno: ", ex);
        return ErrorMessage.builder()
                .date(new Date())
                .message(ex.getMessage())
                .description(request.getDescription(false))
                .build();
    }

}
