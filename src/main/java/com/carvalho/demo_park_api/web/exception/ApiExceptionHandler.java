package com.carvalho.demo_park_api.web.exception;

import com.carvalho.demo_park_api.exception.EntityNotFoundException;
import com.carvalho.demo_park_api.exception.PasswordInvalidException;
import com.carvalho.demo_park_api.exception.UsernameUniqueViolationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(UsernameUniqueViolationException.class)
  public ResponseEntity<ErrorMessage> uniqueViolationException(UsernameUniqueViolationException exception,
                                                                      HttpServletRequest httpServletRequest) {
    log.error("API ERROR: ", exception);
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorMessage(httpServletRequest, HttpStatus.CONFLICT, exception.getMessage()));
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorMessage> entityNotFoundException(EntityNotFoundException exception,
                                                                      HttpServletRequest httpServletRequest) {
    log.error("API ERROR: ", exception);
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorMessage(httpServletRequest, HttpStatus.NOT_FOUND, exception.getMessage()));
  }

  @ExceptionHandler(PasswordInvalidException.class)
  public ResponseEntity<ErrorMessage> passwordInvalidException(PasswordInvalidException exception,
                                                               HttpServletRequest httpServletRequest) {
    log.error("API ERROR: ", exception);
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorMessage(httpServletRequest, HttpStatus.BAD_REQUEST, exception.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                      HttpServletRequest httpServletRequest,
                                                                      BindingResult bindingResult) {
    log.error("API ERROR: ", exception);
    return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorMessage(httpServletRequest, HttpStatus.UNPROCESSABLE_ENTITY, "Campo(s) inválido(s)", bindingResult));
  }
}
