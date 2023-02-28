package com.github.dmitrkuznetsov.exchange_ms.advice;

import com.github.dmitrkuznetsov.exchange_ms.dto.ExceptionResponse;
import com.github.dmitrkuznetsov.exchange_ms.exception.NotEnoughMoney;
import com.github.dmitrkuznetsov.exchange_ms.exception.UnexpectedCurrencyRate;
import com.github.dmitrkuznetsov.exchange_ms.exception.UserAlreadyExistException;
import com.github.dmitrkuznetsov.exchange_ms.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ExceptionResponse handleInvalidArgument(HttpRequestMethodNotSupportedException ex) {

        String[] supportedMethods = ex.getSupportedMethods();
        if (supportedMethods == null) {
            return new ExceptionResponse("Such request is not supported");
        } else {
            List<String> methods = List.of(supportedMethods);
            return new ExceptionResponse("Such request supports only " + methods + " methods");
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ExceptionResponse handleBusinessException(UserNotFoundException ex) {
        return new ExceptionResponse(ex);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistException.class)
    public ExceptionResponse handleBusinessException(UserAlreadyExistException ex) {
        return new ExceptionResponse(ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotEnoughMoney.class)
    public ExceptionResponse handleBusinessException(NotEnoughMoney ex) {
        return new ExceptionResponse(ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnexpectedCurrencyRate.class)
    public ExceptionResponse handleBusinessException(UnexpectedCurrencyRate ex) {
        return new ExceptionResponse(ex);
    }
}
