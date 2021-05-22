package com.cookierdelivery.msproducts.exceptions.handlers;

import com.cookierdelivery.msproducts.exceptions.ValidationException;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ValidationExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ValidationException.class)
  public Map<String, String> onError(ValidationException exception) {
    return exception.getErrors();
  }
}
