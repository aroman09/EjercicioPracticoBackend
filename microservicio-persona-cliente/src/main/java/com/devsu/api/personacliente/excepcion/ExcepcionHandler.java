package com.devsu.api.personacliente.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;


@RestControllerAdvice
public class ExcepcionHandler {

    @ExceptionHandler(Error.class)
    public ResponseEntity<ErrorResponse> handleError(Error ex, WebRequest request){
        ErrorResponse errorResponse= new ErrorResponse(
                LocalDateTime.now(),
                request.getDescription(false),
                ErrorMesagge.EXCEPTION.codError,
                ErrorMesagge.EXCEPTION.msjError);
        switch (ex.getCodeError()){
            case ("997"):
                errorResponse = new ErrorResponse(
                        LocalDateTime.now(),
                        request.getDescription(false),
                        ErrorMesagge.CONFLIC.codError,
                        ErrorMesagge.CONFLIC.msjError
                );
                return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
            case ("996"):
                errorResponse = new ErrorResponse(
                        LocalDateTime.now(),
                        request.getDescription(false),
                        ErrorMesagge.NOT_FOUND.codError,
                        ErrorMesagge.NOT_FOUND.msjError
                );
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleRequestNotSupported(HttpRequestMethodNotSupportedException ex, WebRequest request){
        ErrorResponse errorResponse= new ErrorResponse(
                LocalDateTime.now(),
                request.getDescription(false),
                ErrorMesagge.BAD_PATH.codError,
                ErrorMesagge.BAD_PATH.msjError);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleJson(HttpMessageNotReadableException ex, WebRequest request){
        ErrorResponse errorResponse= new ErrorResponse(
                LocalDateTime.now(),
                request.getDescription(false),
                ErrorMesagge.JSON_FORMAT.codError,
                ErrorMesagge.JSON_FORMAT.msjError);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleJson(MethodArgumentTypeMismatchException ex, WebRequest request){
        ErrorResponse errorResponse= new ErrorResponse(
                LocalDateTime.now(),
                request.getDescription(false),
                ErrorMesagge.EXCEPTION.codError,
                ErrorMesagge.EXCEPTION.msjError);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleJson(MethodArgumentNotValidException ex, WebRequest request){
        ErrorResponse errorResponse= new ErrorResponse(
                LocalDateTime.now(),
                request.getDescription(false),
                ErrorMesagge.PARAMETER_FORMAT.codError,
                ex.getAllErrors().get(0).getDefaultMessage()!=null?
                        ex.getAllErrors().get(0).getDefaultMessage():
                        ErrorMesagge.PARAMETER_FORMAT.msjError);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
