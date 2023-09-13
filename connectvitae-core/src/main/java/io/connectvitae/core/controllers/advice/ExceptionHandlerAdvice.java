package io.connectvitae.core.controllers.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import io.connectvitae.core.controllers.dtos.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  @ExceptionHandler(FeignException.class)
  public ErrorDetails handleFeignException(FeignException ex) {
    if (ex.status() == HttpStatus.UNAUTHORIZED.value()) {
      return new ErrorDetails(
          HttpStatus.SERVICE_UNAVAILABLE,
          "We can't handle your request right now. Please try again later."
      );
    } else if (ex.status() == HttpStatus.FORBIDDEN.value()) {
      ObjectMapper objectMapper = new ObjectMapper();
      try {
        JsonNode jsonNode = objectMapper.readTree(ex.contentUTF8());
        return new ErrorDetails(
            HttpStatus.BAD_REQUEST,
            jsonNode.get("message").asText()
        );
      } catch (JsonProcessingException ignored) {
      }
    }
    return new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
