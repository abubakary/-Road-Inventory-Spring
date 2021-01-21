package com.tarura.RoadInventory.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tz.go.tarura.sharedUtils.Response;
import tz.go.tarura.sharedUtils.ResponseCode;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleValidationExceptions(MethodArgumentNotValidException ex) {

        logger.error(ex.toString());

        Map<String, String> errors = new HashMap<>();
        String errorString = "";
        ex.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);

        });
        Response response = new Response();
        response.setStatus(false);
        response.setCode(ResponseCode.BAD_REQUEST);
        response.setDescription(errors.toString());
        response.setData(null);
        System.out.println(response);
        return response;
    }

    @ExceptionHandler(Exception.class)
    public Response handle(Exception ex) {

        logger.error(ex.toString());

        Response response = new Response();
        if (ex instanceof NullPointerException) {
            response.setCode(ResponseCode.BAD_REQUEST);
            response.setData(null);
            response.setDescription(ex.toString());
            response.setStatus(false);
        }else {
            response.setStatus(false);
            response.setDescription(ex.toString());
            response.setData(null);
            response.setCode(ResponseCode.FAILURE);
        }

        return response;
    }

}

