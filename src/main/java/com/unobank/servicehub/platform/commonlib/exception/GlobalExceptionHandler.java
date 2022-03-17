/*

package com.unobank.servicehub.platform.commonlib.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unobank.servicehub.platform.commonlib.constant.MambuConstants;
import com.unobank.servicehub.platform.commonlib.dto.enums.error.BrankasError;
import com.unobank.servicehub.platform.commonlib.dto.enums.error.ErrorResponse;
import com.unobank.servicehub.platform.commonlib.dto.error.hps.HpsErrorResponse;
import com.unobank.servicehub.platform.commonlib.dto.error.hps.bayad.BayadError;
import com.unobank.servicehub.platform.commonlib.dto.error.hps.bayad.Details;
import com.unobank.servicehub.platform.commonlib.dto.validation.ValidationErrorResponse;
import com.unobank.servicehub.platform.commonlib.util.CommonParser;
import com.unobank.servicehub.platform.commonlib.util.DateUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


*/
/** @author ankur.goel *//*


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends  ResponseEntityExceptionHandler  {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
          MethodArgumentNotValidException ex,
          HttpHeaders headers,
          HttpStatus status,
          WebRequest request) {

    ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult()
            .getAllErrors()
            .forEach(
                    (error) -> {
                      String fieldName = ((FieldError) error).getField();
                      String errorMessage = error.getDefaultMessage();
                      errors.put(fieldName, errorMessage);
                    });
    validationErrorResponse.setErrors(errors);
    validationErrorResponse.setMessage(MambuConstants.FIELD_VALIDATION);
    validationErrorResponse.setTimestamp(
            DateUtil.convertDateToCustomFormatString(LocalDateTime.now()));
    log.error(validationErrorResponse.getErrors().toString());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrorResponse);
  }


  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
    ErrorResponse errorDetails = populateMambuErrorDetail(exception);
    return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleGlobalException(Exception exception) {
    log.error("exception comes as part of flow : {}", exception.getMessage());
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setTimestamp(DateUtil.convertDateToCustomFormatString(LocalDateTime.now()));

    if (exception instanceof ResourceAccessException) {
      errorResponse.addError(new Error((long) HttpStatus.BAD_GATEWAY.value(),"Connection Time Out",""));
      return new ResponseEntity(errorResponse, HttpStatus.BAD_GATEWAY);
    }
    errorResponse.addError(new Error((long) HttpStatus.INTERNAL_SERVER_ERROR.value(),exception.getMessage(),""));
    return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(APIException.class)
  public ResponseEntity<?> handleApiException(APIException exception) {
    ErrorResponse errorDetails = populateMambuErrorDetail(exception);
    log.info("exception comes as part of Mambu flow : {}", CommonParser.parseToJsonString(errorDetails));
    return new ResponseEntity(errorDetails, exception.getStatus());
  }

  @ExceptionHandler(InvalidHeaderFieldException.class)
  public ResponseEntity<?> handleInvalidHeaderFieldException(
      InvalidHeaderFieldException exception) {
    return new ResponseEntity(exception.getMessage(), HttpStatus.PRECONDITION_FAILED);
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<?> handleValidationException(ValidationException exception) {
    ErrorResponse errorDetails = populateValidationErrorDetail(exception);
    log.info("exception comes as part of Validation flow : {}", CommonParser.parseToJsonString(errorDetails));
    return new ResponseEntity(errorDetails, exception.getStatus());
  }

  @ExceptionHandler(BrankasException.class)
  public ResponseEntity<?> brankasExceptionErrorHandler(BrankasException exception) {
    BrankasError errorDetails = populateBrankasErrorDetail(exception);
    log.info("exception comes as part of Brankas flow : {}", CommonParser.parseToJsonString(errorDetails));
    return new ResponseEntity(errorDetails, exception.getStatus());
  }

  @ExceptionHandler(BayadException.class)
  public ResponseEntity<?> bayadExceptionErrorHandler(BayadException exception) {
    BayadError errorDetails = populateBayadErrorDetail(exception);
    log.info("exception comes as part of Bayad flow : {}", CommonParser.parseToJsonString(errorDetails));
    return new ResponseEntity(errorDetails, exception.getStatus());
  }

  @ExceptionHandler(HPSException.class)
  public ResponseEntity<?> hpsExceptionErrorHandler(HPSException exception) {
    HpsErrorResponse errorDetails = populateHPSErrorDetail(exception);
    log.info("exception comes as part of HPS flow : {}", CommonParser.parseToJsonString(errorDetails));
    return new ResponseEntity(errorDetails, exception.getStatus());
  }

  private HpsErrorResponse populateHPSErrorDetail(HPSException exception) {
    HpsErrorResponse errorResponse = null;
    try {
      errorResponse =
              (HpsErrorResponse)
                      CommonParser.convertStringToPojo(HpsErrorResponse.class, exception.getMessage());
    }catch (JsonProcessingException ex) {
      log.error("failed while parsing error response from HPS api with exception {}", ex.getMessage());
    }
    return errorResponse;
  }

  private ErrorResponse populateMambuErrorDetail(Exception exception) {
    ErrorResponse errorResponse = null;
    try {
      errorResponse =
              (ErrorResponse)
                      CommonParser.convertStringToPojo(ErrorResponse.class, exception.getMessage());
      errorResponse.setTimestamp(DateUtil.convertDateToCustomFormatString(LocalDateTime.now()));
    } catch (JsonProcessingException ex) {
      log.error("failed while parsing error response from mambu api with exception {}", ex.getMessage());
    }
    return errorResponse;
  }

  private BrankasError populateBrankasErrorDetail(Exception exception) {
    BrankasError brankasError = null;
    try {
      brankasError =
              (BrankasError)
                      CommonParser.convertStringToPojo(BrankasError.class, exception.getMessage());
      brankasError.setTimestamp(DateUtil.convertDateToCustomFormatString(LocalDateTime.now()));
    } catch (JsonProcessingException ex) {
      log.error("failed while parsing error response from brankas api with exception {}", ex.getMessage());
    }
    return brankasError;
  }

  private BayadError populateBayadErrorDetail(BayadException exception) {
    BayadError bayadError = null;
    try {
      if(exception.getStatus().equals(HttpStatus.BAD_REQUEST)) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode badRequestObject = mapper.readTree(exception.getMessage());
        Details details = new Details();
        details.setMessage(badRequestObject.get("details").textValue());
        return new BayadError(DateUtil.convertDateToCustomFormatString(LocalDateTime.now()), details,
                badRequestObject.get("exception").textValue(), null, 400L );
      }
      bayadError = (BayadError) CommonParser.convertStringToPojo(BayadError.class, exception.getMessage());
      bayadError.setTimestamp(DateUtil.convertDateToCustomFormatString(LocalDateTime.now()));
    } catch (JsonProcessingException ex) {
      log.error("failed while parsing error response from bayad api with exception {}", ex.getMessage());
    }
    return bayadError;
  }

  private ErrorResponse populateValidationErrorDetail(Exception exception) {
    ErrorResponse response = new ErrorResponse();
    response.setTimestamp(DateUtil.convertDateToCustomFormatString(LocalDateTime.now()));
    List<Error> errors = new ArrayList<>();
    errors.add(new Error(101L, exception.getMessage(), ""));
    response.setErrors(errors);
    return response;
  }
}

*/
