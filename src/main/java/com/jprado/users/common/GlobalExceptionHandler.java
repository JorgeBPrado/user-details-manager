package com.jprado.users.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private static final String UNEXPECTED_EXCEPTION_ERROR = "exception.unexpected";
    private static final String APPLICATION_EXCEPTION_ERROR = "application.error";
    
    private final MessageSource messageSource;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionMessage> handleValidationException(ConstraintViolationException error, Locale locale) {
        
    	log.error(error.getMessage(), error);

        List<String> messages = error.getConstraintViolations()
                .stream()
                .map( constrain -> constrain.getPropertyPath() + constrain.getMessage())
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ExceptionMessage(messages), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ExceptionMessage> handleExceptions(AppException error, Locale locale) {
        
    	log.error(error.getMessage(), error);
        
    	String errorMessage = messageSource.getMessage(APPLICATION_EXCEPTION_ERROR, null, locale);
        
    	return new ResponseEntity<>(new ExceptionMessage(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionMessage> handleExceptions(Exception error, Locale locale) {
        
    	log.error(error.getMessage(), error);
        
    	String errorMessage = messageSource.getMessage(UNEXPECTED_EXCEPTION_ERROR, null, locale);
        
    	return new ResponseEntity<>(new ExceptionMessage(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
