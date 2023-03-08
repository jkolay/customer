package com.carlease.customer.exception;

import com.carlease.customer.config.CustomerErrorCodeConfig;
import com.carlease.customer.model.error.CustomerErrorModel;
import com.carlease.customer.model.error.CustomerRequestErrorModel;
import com.carlease.customer.model.error.ErrorSeverityLevelCodeType;
import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

/** This is the custom exception handler class */
@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      Exception ex,
      @Nullable Object body,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
      request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
    }

    return new ResponseEntity(body, headers, status);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {

    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });
    final CustomerRequestErrorModel error =
        new CustomerRequestErrorModel(
            errors, CustomerErrorCodeConfig.INVALID_INPUT, ErrorSeverityLevelCodeType.ERROR);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(CustomerNotFoundException.class)
  @ResponseBody
  public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex) {
    final CustomerErrorModel error =
        new CustomerErrorModel(
            ex.getMessage(),
            CustomerErrorCodeConfig.INVALID_INPUT,
            ErrorSeverityLevelCodeType.ERROR);
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(CustomerException.class)
  @ResponseBody
  public ResponseEntity<Object> handleCustomerException(CustomerException ex) {
    final CustomerErrorModel error =
        new CustomerErrorModel(
            ex.getMessage(),
            CustomerErrorCodeConfig.INVALID_INPUT,
            ErrorSeverityLevelCodeType.ERROR);
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(CustomerDuplicationException.class)
  @ResponseBody
  public ResponseEntity<Object> handleCustomerDuplicationException(
      CustomerDuplicationException ex) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    final CustomerErrorModel error =
        new CustomerErrorModel(
            ex.getMessage(),
            CustomerErrorCodeConfig.INVALID_INPUT,
            ErrorSeverityLevelCodeType.ERROR);
    return new ResponseEntity<>(error, status);
  }

  @ExceptionHandler({IllegalArgumentException.class, InvalidDataAccessApiUsageException.class})
  @ResponseBody
  public ResponseEntity<Object> handleArgumentException(Exception ex) {
    final CustomerErrorModel error =
        new CustomerErrorModel(
            ex.getMessage(),
            CustomerErrorCodeConfig.INVALID_INPUT,
            ErrorSeverityLevelCodeType.ERROR);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseBody
  public ResponseEntity<Object> handleDataIntegrityViolationException(
      DataIntegrityViolationException ex) {
    String message = ex.getMessage();
    if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
      message = CustomerErrorCodeConfig.DB_ERROR;
    }

    final CustomerErrorModel error =
        new CustomerErrorModel(
            message, CustomerErrorCodeConfig.DB_ERROR, ErrorSeverityLevelCodeType.ERROR);
    return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseBody
  public final ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
    final CustomerErrorModel error =
        new CustomerErrorModel(
            ex.getMessage(), CustomerErrorCodeConfig.DB_ERROR, ErrorSeverityLevelCodeType.ERROR);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseBody
  public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
    final CustomerErrorModel error =
        new CustomerErrorModel(
            ex.getMessage(),
            CustomerErrorCodeConfig.INTERNAL_ERROR,
            ErrorSeverityLevelCodeType.ERROR);
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseEntity<Object> handleGlobalException(Exception ex) {
    final CustomerErrorModel error =
        new CustomerErrorModel(
            ex.getMessage(),
            CustomerErrorCodeConfig.GLOBAL_ERROR,
            ErrorSeverityLevelCodeType.ERROR);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
