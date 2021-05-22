package com.cookierdelivery.msproducts.exceptions;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ValidationException extends IllegalArgumentException {

  private static final long serialVersionUID = 1L;

  @Getter private final Map<String, String> errors;
}
