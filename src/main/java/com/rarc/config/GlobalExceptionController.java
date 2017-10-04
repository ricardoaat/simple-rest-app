package com.rarc.config;

import com.rarc.rest.ApiError;
import io.jsonwebtoken.JwtException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@Order(Ordered.LOWEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<ApiError> handleInternalError(RuntimeException ex, WebRequest request){
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
        error.setDebugMessage(ex.getMessage());
        error.setMessage("Internal server error");

        return new ResponseEntity<ApiError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JwtException.class)
    protected ResponseEntity<ApiError> jwtGeneralError (
        HttpServletRequest request, Exception ex){
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
        error.setDebugMessage(ex.getMessage());
        error.setMessage("Internal server error");        

        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
    }

   /*
    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<ApiError> handleEverything(Exception ex, WebRequest request){
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
        error.setDebugMessage(ex.getMessage());
        error.setMessage("Something went terribly wrong");
        ex.printStackTrace();

        return new ResponseEntity<ApiError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    */    
}