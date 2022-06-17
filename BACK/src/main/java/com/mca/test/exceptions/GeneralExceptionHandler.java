package com.mca.test.exceptions;


import com.mca.test.response.ResponseErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GeneralExceptionHandler.class);

    @ExceptionHandler({ APIException.class })
    public ResponseEntity<Object> handleApiException(Exception ex, WebRequest request) {
        logger.error(ex.getMessage());
        return new ResponseEntity<Object>(new ResponseErrorDTO("fail",ex.getMessage()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ ResourceNotFoundException.class })
    public ResponseEntity<Object> handleResourceNotFoundException(Exception ex, WebRequest request) {
        logger.error(ex.getMessage());
        return new ResponseEntity<Object>(new ResponseErrorDTO("fail",ex.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}