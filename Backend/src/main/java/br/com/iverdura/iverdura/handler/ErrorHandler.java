package br.com.iverdura.iverdura.handler;

import br.com.iverdura.iverdura.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BusinessException.class)
    public VerdureErrorResponse businessErrorHandler(BusinessException businessException){
        return new VerdureErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,businessException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public VerdureErrorResponse constraintErrorHandler(MethodArgumentNotValidException constraintValidationException){
        String error =  constraintValidationException.getMessage();
        return new VerdureErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, error);
    }
}
