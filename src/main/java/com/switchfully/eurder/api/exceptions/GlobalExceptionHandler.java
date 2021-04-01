package com.switchfully.eurder.api.exceptions;

import com.switchfully.eurder.infrastructure.exceptions.CustomerHasNoOrderException;
import com.switchfully.eurder.infrastructure.exceptions.CustomerNotFoundException;
import com.switchfully.eurder.infrastructure.exceptions.InvalidEmailException;
import com.switchfully.eurder.infrastructure.exceptions.InvalidPhoneNumberException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalAccessException.class)
    protected void illegalAccessException(IllegalAccessException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied: " + exception.getMessage());
    }

    @ExceptionHandler(InvalidEmailException.class)
    private void invalidEmailException(InvalidEmailException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(InvalidPhoneNumberException.class)
    private void invalidPhoneNumberException(InvalidPhoneNumberException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(CustomerHasNoOrderException.class)
    private void customerWithNoOrderException(CustomerHasNoOrderException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NO_CONTENT, exception.getMessage());
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    private void customerNotFoundException(CustomerNotFoundException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, exception.getMessage());
    }

}
