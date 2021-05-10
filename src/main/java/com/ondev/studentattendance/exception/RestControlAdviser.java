package com.ondev.studentattendance.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;


@RestControllerAdvice()
@Slf4j
public class RestControlAdviser {

    @ExceptionHandler({HttpCustomException.class})
    public HttpErrorResponse handleCustomerException(HttpCustomException exception, HttpServletResponse response) {

        HttpErrorResponse httpErrorResponse = new HttpErrorResponse(exception.getErrorCode(),
                exception.getErrorResponse());
        //215 : is a custom status code for all our custom http exception response.
        response.setStatus(215);
        log.error("HttpCustomException => error code : {} , details : {}", exception.getErrorCode(),
                exception.getErrorResponse());
        return httpErrorResponse;
    }
}
