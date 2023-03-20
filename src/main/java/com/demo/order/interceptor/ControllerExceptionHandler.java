package com.demo.order.interceptor;

import com.demo.order.common.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("argument invalid with error code: {}", HttpStatus.BAD_REQUEST.value());
        log.warn("exception message: {}", e.getMessage());
        ErrorResult errorResult = new ErrorResult(HttpStatus.BAD_REQUEST.value(), "Invalid Argument");
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorResult.writeDetails(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errorResult;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorResult handleException(Exception e) {
        log.warn("handle exception", e);
        return new ErrorResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
