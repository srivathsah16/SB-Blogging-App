package com.srivath.blog.app.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.srivath.blog.app.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
     * Whenever an exception of type ResourceNotFoundException.class occurs in
     * Controllers/or anywhere in the application, this method will be called to handle the exception.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        System.out.println("ResourceNotFoundExceptionHandler method triggered");
        String message = ex.getMessage();
        return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> MethodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException ex) {

        System.out.println("MethodArgumentNotValidExceptionHandler method triggered");

        Map<String, String> messageMap = new HashMap<>();

        // note: Typecaste error into FieldError in order to get Field from it.

        ex.getBindingResult().getAllErrors().stream().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            messageMap.put(fieldName, errorMessage);
        });

        return new ResponseEntity<Map<String, String>>(messageMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse> noResourceFoundExceptionhandler(NoResourceFoundException e) {
        System.out.println("noResourceFoundExceptionhandler method triggered!");
        ApiResponse response = new ApiResponse(e.getMessage(), false);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
    }
}
