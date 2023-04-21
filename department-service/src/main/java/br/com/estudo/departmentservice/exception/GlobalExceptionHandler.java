package br.com.estudo.departmentservice.exception;

import br.com.estudo.departmentservice.dto.ErrorDetailsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class )
    public ResponseEntity<ErrorDetailsDTO> handleResourceNotFoundException(
            ResourceNotFoundException exception, WebRequest webRequest) {

        ErrorDetailsDTO errorDto = new ErrorDetailsDTO(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(Boolean.FALSE),
                "RESOURCE_NOT_FOUND");

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class )
    public ResponseEntity<ErrorDetailsDTO> handleGlobalException(
            Exception exception, WebRequest webRequest) {

        ErrorDetailsDTO errorDto = new ErrorDetailsDTO(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(Boolean.FALSE),
                "INTERNAL_SERVER_ERROR");

        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class )
    public ResponseEntity<ErrorDetailsDTO> handleResourceAlreadyExistsException(
            ResourceAlreadyExistsException exception, WebRequest webRequest) {

        ErrorDetailsDTO errorDto = new ErrorDetailsDTO(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(Boolean.FALSE),
                "CONFLICT");

        return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
    }
}
