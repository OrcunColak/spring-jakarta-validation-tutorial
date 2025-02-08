package com.colak.springtutorial.annotationcontroller.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionTranslator {

    // Old code to return Map<String, String>
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public Map<String, String> handleMethodArgumentNotValidExceptions(
    //         MethodArgumentNotValidException ex) {
    //     Map<String, String> errors = new HashMap<>();
    //     BindingResult bindingResult = ex.getBindingResult();
    //     List<ObjectError> allErrors = bindingResult.getAllErrors();
    //
    //     allErrors.forEach(error -> {
    //         FieldError fieldError = (FieldError) error;
    //         String fieldName = fieldError.getField();
    //         String errorMessage = fieldError.getDefaultMessage();
    //         errors.put(fieldName, errorMessage);
    //     });
    //     return errors;
    // }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidExceptions(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed");

        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        fieldErrors.forEach(fieldError -> {
            String fieldName = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();
            problemDetail.setProperty(fieldName, errorMessage);
        });

        return problemDetail;
    }
}